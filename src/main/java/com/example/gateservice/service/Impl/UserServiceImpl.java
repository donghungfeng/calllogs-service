package com.example.gateservice.service.Impl;


import com.example.gateservice.config.ModelMapperConfig;
import com.example.gateservice.config.jwt.CustomUserDetails;
import com.example.gateservice.config.jwt.JwtTokenProvider;
import com.example.gateservice.model.*;
import com.example.gateservice.query.CustomRsqlVisitor;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.reponse.LoginReponse;
import com.example.gateservice.repository.*;
import com.example.gateservice.request.CreateUserRequest;
import com.example.gateservice.request.LoginRequest;
import com.example.gateservice.service.DepartmentService;
import com.example.gateservice.service.UserService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    RoleUserRepository roleUserRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DepartmentService departmentService;
    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    public BaseResponse createUser(CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {
        Department department = departmentRepository.getDepartmentById(createUserRequest.getDepartmentId());
        String roleCode= roleRepository.findRoleById(createUserRequest.getRoleId()).getCode();
        User user = modelMapper.map(createUserRequest, User.class);
        user.setPassword(enCode(user.getPassword()));
        user.setDepartment(department);
        user = userRepository.save(user);
        Role role = roleRepository.findAllById(createUserRequest.getRoleId());
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(user.getId());
        roleUser.setRoleId(role.getId());
        roleUser.setCode(roleCode);
        roleUserRepository.save(roleUser);
        user.setPassword(null);
        return new BaseResponse(200, "OK", user);
    }

    @Override
    public BaseResponse updateUser(CreateUserRequest updateUserRequest) throws NoSuchAlgorithmException {
        Department department = departmentRepository.getDepartmentById(updateUserRequest.getDepartmentId());
        User user = modelMapper.map(updateUserRequest, User.class);
        user.setPassword(enCode(user.getPassword()));
        user.setDepartment(department);
        user = userRepository.save(user);
        Role role = roleRepository.findAllById(updateUserRequest.getRoleId());
        String roleCode= roleRepository.findRoleById(updateUserRequest.getRoleId()).getCode();
        List<RoleUser> roleUserList = roleUserRepository.findAllByUserId(updateUserRequest.getId());
        RoleUser roleUser = roleUserList.get(0);
        if (!roleUserList.isEmpty()){
            roleUser.setCode(roleCode);
            roleUser.setRoleId(updateUserRequest.getRoleId());
        }else {
            roleUser.setCode(roleCode);
            roleUser.setUserId(user.getId());
            roleUser.setRoleId(role.getId());
        }
        roleUserRepository.save(roleUser);
        user.setPassword(null);
        return new BaseResponse(200, "OK", user);
    }

    @Override
    public BaseResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserName(loginRequest.getUserName());
        if (user == null){
            return new BaseResponse(500, "Account không tồn tại", null);
        }
        String password = enCode(loginRequest.getPassword());
        if (!user.getPassword().equals(password)){
            return new BaseResponse(500, "Mật khẩu không chính xác", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        List<RoleUser> roleUserList = roleUserRepository.findAllByUserId(user.getId());
        List<Long> roleIdList = new ArrayList<>();
        for (RoleUser item : roleUserList){
            roleIdList.add(item.getRoleId());
        }

        List<String> roleList = roleRepository.findAllByInIds(roleIdList).stream().map(item -> item.getName()).collect(Collectors.toList());
        Long departmentId;
        if(user.getDepartment()!=null){
            departmentId=user.getDepartment().getId();
        }else{
            departmentId=null;
        }

        LoginReponse loginReponse = new LoginReponse(jwtTokenProvider.generateToken(new CustomUserDetails(roleUserRepository, user)), user.getId(), departmentId, roleList);
        return new BaseResponse(200, "OK", loginReponse);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


    public String enCode(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }
}

