package com.example.gateservice.controller;

import com.example.gateservice.model.User;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.request.CreateUserRequest;
import com.example.gateservice.request.LoginRequest;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("user")
public class UserController extends BaseController<User> {
    @Autowired
    UserService userService;
    @Override
    protected BaseService<User> getService() {
        return userService;
    }

    @PostMapping("create")
    public BaseResponse create(@RequestBody CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {
        return userService.createUser(createUserRequest);
    }

    @PostMapping("login")
    public BaseResponse login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        return userService.login(loginRequest);
    }


}
