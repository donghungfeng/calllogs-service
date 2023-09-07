package com.example.gateservice.controller;

import com.example.gateservice.model.Department;
import com.example.gateservice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController<Department>{
    @Autowired
    @Override
    protected BaseService<Department> getService() {
        return null;
    }
}
