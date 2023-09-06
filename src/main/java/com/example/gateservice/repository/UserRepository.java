package com.example.gateservice.repository;

import com.example.gateservice.model.User;

public interface UserRepository extends BaseRepository<User>{
    User findByUserName(String userName);
}
