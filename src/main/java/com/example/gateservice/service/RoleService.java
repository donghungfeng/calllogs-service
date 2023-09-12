package com.example.gateservice.service;


import com.example.gateservice.model.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> findAllByInIds(List<Long> ids);
}
