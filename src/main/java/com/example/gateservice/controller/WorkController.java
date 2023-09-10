package com.example.gateservice.controller;

import com.example.gateservice.model.Work;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.request.CreateUserRequest;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("work")
@CrossOrigin
public class WorkController extends BaseController<Work>{
    @Autowired
    WorkService workService;
    @Override
    protected BaseService<Work> getService() {
        return workService;
    }

    @PostMapping("checkin")
    public BaseResponse checkin(@RequestHeader(name = "Authorization") String jwt) throws NoSuchAlgorithmException {
        Work work = workService.checkIn(jwt);
        if (work == null){
            return new BaseResponse().fail("Nhân viên đã đăng nhập");
        }
        return new BaseResponse().success(work);
    }

    @PostMapping("checkout")
    public BaseResponse checkOut(@RequestHeader(name = "Authorization") String jwt) throws NoSuchAlgorithmException {
        Work work = workService.checkOut(jwt);
        if (work == null){
            return new BaseResponse().fail("Tài khoản hiện không đăng nhập ở đâu");
        }
        return new BaseResponse().success(work);
    }
}
