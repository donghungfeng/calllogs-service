package com.example.gateservice.repository;

import com.example.gateservice.model.CallInfo;
import com.example.gateservice.model.User;

import java.util.List;

public interface CallInfoRepository extends BaseRepository<CallInfo>{
    List<CallInfo> findCallInfoByIsActive(int status);

    CallInfo findCallInfoByStaff(User staff);
}
