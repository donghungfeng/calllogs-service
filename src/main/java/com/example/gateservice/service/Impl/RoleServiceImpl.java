package com.example.gateservice.service.Impl;


import com.example.gateservice.model.Role;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.RoleRepository;
import com.example.gateservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    protected BaseRepository<Role> getRepository() {
        return roleRepository;
    }
}

