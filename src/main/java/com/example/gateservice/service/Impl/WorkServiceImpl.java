package com.example.gateservice.service.Impl;

import com.example.gateservice.config.jwt.JwtTokenProvider;
import com.example.gateservice.model.*;
import com.example.gateservice.query.CustomRsqlVisitor;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.WorkRepository;
import com.example.gateservice.service.*;
import com.example.gateservice.util.DateUtil;
import com.example.gateservice.util.JwtUtil;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl extends BaseServiceImpl<Work> implements WorkService {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkRepository workRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleUserService roleUserService;

    @Autowired
    CallInfoService callInfoService;

    private final static String MANAGER = "manager";
    private final static String LEADER = "leader";
    private final static String STAFF = "staff";

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    protected BaseRepository<Work> getRepository() {
        return workRepository;
    }
    private String getJwtFromRequest(String bearerToken) {
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public Work checkIn(String jwt) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String bearerToken = getJwtFromRequest(jwt);
        String userName = jwtTokenProvider.getAccountUserNameFromJWT(bearerToken);
        User user = userService.getByUserName(userName);
        if (workRepository.findAllByStaffAndIsActive(user, 1) != null){
            return null;
        }
        Work work = new Work();
        work.setStaff(user);
        work.setTimeIn(DateUtil.getCurrenDateHour());
        executorService.submit(() -> {
            List<CallInfo> callInfoList = callInfoService.findAllByIsActive(1);
            if (!callInfoList.isEmpty()){
                Random rand = new Random();
                int ranNum = rand.nextInt(callInfoList.size());
                callInfoList.get(ranNum).setStaff(user);
                callInfoService.create(callInfoList.get(ranNum));
            }
        });
        work = workRepository.save(work);
        return work;
    }

    @Override
    public Work checkOut(String jwt) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String bearerToken = getJwtFromRequest(jwt);
        String userName = jwtTokenProvider.getAccountUserNameFromJWT(bearerToken);
        User user = userService.getByUserName(userName);
        Work work = workRepository.findAllByStaffAndIsActive(user, 1);
        if (work == null){
            return null;
        }
        work.setTimeOut(DateUtil.getCurrenDateHour());
        work.setIsActive(-1);
        CallInfo callInfo = callInfoService.findAllByStaff(user);
        if (callInfo != null){
            callInfo.setStaff(null);
            callInfoService.create(callInfo);
        }
        work = workRepository.save(work);
        return work;
    }

    @Override
    public Work checkActive(String jwt) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String bearerToken = getJwtFromRequest(jwt);
        String userName = jwtTokenProvider.getAccountUserNameFromJWT(bearerToken);
        User user = userService.getByUserName(userName);
        Work work = workRepository.findAllByStaffAndIsActive(user, 1);
        return work;
    }

//    @Override
//    public Page<Work> search(String filter, String sort, int size, int page, String jwt){
//        Node rootNode = new RSQLParser().parse(filter);
//        Specification<Work> spec = rootNode.accept(new CustomRsqlVisitor<Work>());
//        String[] sortList = sort.split(",");
//        Sort.Direction direction = sortList[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Pageable pageable = PageRequest.of(page, size, direction, sortList[0]);
//        Page<Work> workPage =  this.getRepository().findAll(spec, pageable);
//
//        List<Work> workList = workPage.getContent();
//        List<Work> resultList = new ArrayList<>();
//        User user = jwtUtil.getUserByJwt(jwt);
//        List<RoleUser> roleUserList = roleUserService.findRoleUserByUserId(user.getId());
//        List<Long> roleIdList = roleUserList.stream().map(e->e.getRoleId()).collect(Collectors.toList());
//        List<Role> roleList = roleService.findAllByInIds(roleIdList);
//        if (checkRole(roleList, MANAGER) || checkRole(roleList, "admin")){
//            return workPage;
//        }
//        if (checkRole(roleList, LEADER)){
//            for (var work : workList){
//                if (work.getStaff().getDepartment() != null && user.getDepartment() != null){
//                    if (work.getStaff().getDepartment().getId().equals(user.getDepartment().getId())){
//                        resultList.add(work);
//                    }
//                }
//            }
//            return new PageImpl<>(resultList);
//        }
//        if (checkRole(roleList, STAFF)){
//            for (var work : workList){
//                if (work.getStaff().getDepartment() != null && user.getDepartment() != null && work.getStaff().getDepartment() != null){
//                    if (work.getStaff().getDepartment().getId().equals(user.getDepartment().getId()) && work.getStaff().getDepartment().getId().equals(user.getId())){
//                        resultList.add(work);
//                    }
//                }
//            }
//            return new PageImpl<>(resultList);
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
