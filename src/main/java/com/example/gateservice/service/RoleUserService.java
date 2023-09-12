package com.example.gateservice.service;

import com.example.gateservice.model.RoleUser;

import java.util.List;

public interface RoleUserService extends BaseService<RoleUser> {
    List<RoleUser> findRoleUserByUserId(Long userId);
}
