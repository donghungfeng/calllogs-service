package com.example.gateservice.service.Impl;

import com.example.gateservice.model.Department;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.DepartmentRepository;
import com.example.gateservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {
    @Autowired
    DepartmentRepository deparmentRepository;
    @Override
    protected BaseRepository<Department> getRepository() {
        return deparmentRepository;
    }
}
