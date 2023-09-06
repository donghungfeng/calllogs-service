package com.example.gateservice.controller;

import com.example.gateservice.model.Role;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController<Role> {
    @Autowired
    RoleService roleService;
    @Override
    protected BaseService<Role> getService() {
        return roleService;
    }
}
