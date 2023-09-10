package com.example.gateservice.service.Impl;

import com.example.gateservice.config.jwt.JwtTokenProvider;
import com.example.gateservice.model.User;
import com.example.gateservice.model.Work;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.WorkRepository;
import com.example.gateservice.service.UserService;
import com.example.gateservice.service.WorkService;
import com.example.gateservice.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WorkServiceImpl extends BaseServiceImpl<Work> implements WorkService {

    @Autowired
    private UserService userService;
    @Autowired
    private WorkRepository workRepository;
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
        work = workRepository.save(work);
        return work;
    }
}
