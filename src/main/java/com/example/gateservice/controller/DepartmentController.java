package com.example.gateservice.controller;

import com.example.gateservice.model.Department;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
@CrossOrigin
public class DepartmentController extends BaseController<Department>{
    @Autowired
    DepartmentService departmentService;
    @Override
    protected BaseService<Department> getService() {
        return departmentService;
    }
}
