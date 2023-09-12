package com.example.gateservice.repository;

import com.example.gateservice.model.Role;
import com.example.gateservice.model.RoleUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleUserRepository extends BaseRepository<RoleUser>{
    List<RoleUser> findAllByUserId(Long id);

    @Query(value = "select * from role_user where user_id in :ids", nativeQuery = true)
    List<RoleUser> findAllByInUserIds(List<Long> ids);
}
