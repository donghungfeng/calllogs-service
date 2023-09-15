package com.example.gateservice.service.Impl;

import com.example.gateservice.dtos.UserDto;
import com.example.gateservice.model.Data;
import com.example.gateservice.model.Role;
import com.example.gateservice.model.RoleUser;
import com.example.gateservice.model.User;
import com.example.gateservice.query.CustomRsqlVisitor;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.DataRepository;
import com.example.gateservice.request.data.AssignDataItemRequest;
import com.example.gateservice.request.data.AssignDataRequest;
import com.example.gateservice.service.DataService;
import com.example.gateservice.service.RoleService;
import com.example.gateservice.service.RoleUserService;
import com.example.gateservice.util.JwtUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl extends BaseServiceImpl<Data> implements DataService {
    @Autowired
    DataRepository dataRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleUserService roleUserService;

    private final static String MANAGER = "manager";
    private final static String LEADER = "leader";
    private final static String STAFF = "staff";
    @Override
    protected BaseRepository<Data> getRepository() {
        return dataRepository;
    }

    @Override
    public void saveCustomersToDatabase(MultipartFile file, Long departmentId){
        if(UploadFileServiceImpl.isValidExcelFile(file)){
            try {
                List<Data> customers = UploadFileServiceImpl.getCustomersDataFromExcel(file.getInputStream(), departmentId);
                this.dataRepository.saveAll(customers);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    @Override
    public String assignData(AssignDataRequest assygnDataRequest) {
        List<Data> resultList = new ArrayList<>();
        for (AssignDataItemRequest item : assygnDataRequest.getItem()){
            Data data = dataRepository.findAllById(item.getDataId());
            if (data != null){
                User user = new User();
                user.setId(item.getUserId());
                data.setStaff(user);
                resultList.add(data);
            }
        }
        dataRepository.saveAll(resultList);
        return "Assygn data success!";
    }

//    @Override
//    public Page<Data> search(String filter, String sort, int size, int page, String jwt){
//        Node rootNode = new RSQLParser().parse(filter);
//        Specification<Data> spec = rootNode.accept(new CustomRsqlVisitor<Data>());
//        String[] sortList = sort.split(",");
//        Sort.Direction direction = sortList[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortList[0]);
//        Page<Data> dataPage =  this.getRepository().findAll(spec, pageable);
//        List<Data> dataList = dataPage.getContent();
//        List<Data> resultList = new ArrayList<>();
//        User user = jwtUtil.getUserByJwt(jwt);
//        List<RoleUser> roleUserList = roleUserService.findRoleUserByUserId(user.getId());
//        List<Long> roleIdList = roleUserList.stream().map(e->e.getRoleId()).collect(Collectors.toList());
//        List<Role> roleList = roleService.findAllByInIds(roleIdList);
//        if (checkRole(roleList, MANAGER) || checkRole(roleList, "admin")){
//            return dataPage;
//        }
//        if (checkRole(roleList, LEADER)){
//            for (var data : dataList){
//                if (data.getDepartment() != null && user.getDepartment() != null){
//                    if (data.getDepartment().equals(user.getDepartment())){
////                        if (data.getStaff() != null){
////                            data.getStaff().setPassword(null);
////                        }
//                        resultList.add(data);
//                    }
//                }
//            }
//            return new PageImpl<>(resultList, pageable, dataPage.getTotalElements());
//        }
//        if (checkRole(roleList, STAFF)){
//            for (var data : dataList){
//                if (data.getDepartment() != null && user.getDepartment() != null && data.getStaff() != null){
//                    if (data.getDepartment().getId().equals(user.getDepartment().getId()) && data.getStaff().getId().equals(user.getId())){
//                        data.getStaff().setPassword("");
//                        resultList.add(data);
//                    }
//                }
//            }
//            return new PageImpl<>(resultList, pageable, dataPage.getTotalElements());
//        }
//        return new PageImpl<>(resultList);
//    }

    private Boolean checkRole(List<Role> roleList, String role){
        for (var item : roleList){
            if (item.getName().equals(role))
                return true;
        }
        return false;
    }
}
