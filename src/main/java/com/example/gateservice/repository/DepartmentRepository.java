package com.example.gateservice.repository;

import com.example.gateservice.model.Department;

public interface DepartmentRepository extends BaseRepository<Department>{
    Department getDepartmentById(Long id);
}
