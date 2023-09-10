package com.example.gateservice.repository;

import com.example.gateservice.model.User;
import com.example.gateservice.model.Work;

public interface WorkRepository extends BaseRepository<Work>{
    public Work findAllByStaffAndIsActive(User user, int isActive);
}
