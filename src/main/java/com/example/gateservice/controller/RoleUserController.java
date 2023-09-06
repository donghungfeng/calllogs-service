package com.example.gateservice.controller;


import com.example.gateservice.model.RoleUser;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role_user")
public class RoleUserController extends BaseController<RoleUser> {
    @Autowired
    RoleUserService roleUserService;
    @Override
    protected BaseService<RoleUser> getService() {
        return roleUserService;
    }
}
