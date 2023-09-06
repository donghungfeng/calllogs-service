package com.example.gateservice.service;

import com.example.gateservice.model.User;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.request.CreateUserRequest;
import com.example.gateservice.request.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface UserService extends BaseService<User>{
    public BaseResponse createUser(CreateUserRequest createUserRequest) throws NoSuchAlgorithmException;
    public BaseResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException;
}
