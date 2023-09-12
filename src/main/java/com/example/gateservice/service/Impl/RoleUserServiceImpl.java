package com.example.gateservice.service.Impl;

import com.example.gateservice.model.RoleUser;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.RoleUserRepository;
import com.example.gateservice.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUser> implements RoleUserService {
    @Autowired
    RoleUserRepository roleUserRepository;
    @Override
    protected BaseRepository<RoleUser> getRepository() {
        return roleUserRepository;
    }

    @Override
    public List<RoleUser> findRoleUserByUserId(Long userId) {
        return roleUserRepository.findAllByUserId(userId);
    }
}
