package com.example.gateservice.repository;

import com.example.gateservice.model.RoleUser;

import java.util.List;

public interface RoleUserRepository extends BaseRepository<RoleUser>{
    List<RoleUser> findAllByUserId(Long id);
}
