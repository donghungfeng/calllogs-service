package com.example.gateservice.service;

import com.example.gateservice.model.CallInfo;
import com.example.gateservice.model.User;

import java.util.List;

public interface CallInfoService extends BaseService<CallInfo>{
    List<CallInfo> findAllByIsActive(int status);
    CallInfo findAllByStaff(User Staff);
}
