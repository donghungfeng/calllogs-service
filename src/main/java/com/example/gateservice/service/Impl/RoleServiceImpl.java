package com.example.gateservice.service.Impl;


import com.example.gateservice.model.Role;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.RoleRepository;
import com.example.gateservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    protected BaseRepository<Role> getRepository() {
        return roleRepository;
    }

    @Override
    public List<Role> findAllByInIds(List<Long> ids) {
        return roleRepository.findAllByInIds(ids);
    }
}

