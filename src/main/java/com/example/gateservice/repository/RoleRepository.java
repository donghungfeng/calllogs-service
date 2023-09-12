package com.example.gateservice.repository;

import com.example.gateservice.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseRepository<Role>{
    @Query(value = "select * from role where id in :ids", nativeQuery = true)
    List<Role> findAllByInIds(List<Long> ids);

    @Query(value = "select * from role where staff_id in :ids", nativeQuery = true)
    List<Role> findAllByInUserIds(List<Long> ids);
}
