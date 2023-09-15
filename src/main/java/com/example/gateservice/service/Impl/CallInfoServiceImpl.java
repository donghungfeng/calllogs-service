package com.example.gateservice.service.Impl;

import com.example.gateservice.model.CallInfo;
import com.example.gateservice.model.CallLogs;
import com.example.gateservice.model.User;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.CallInfoRepository;
import com.example.gateservice.repository.CallLogsRepository;
import com.example.gateservice.service.CallInfoService;
import com.example.gateservice.service.CalllLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallInfoServiceImpl extends BaseServiceImpl<CallInfo> implements CallInfoService {
    @Autowired
    CallInfoRepository callInfoRepository;
    @Override
    protected BaseRepository<CallInfo> getRepository() {
        return callInfoRepository;
    }

    @Override
    public List<CallInfo> findAllByIsActive(int status) {
        return callInfoRepository.findCallInfoByIsActive(status);
    }

    @Override
    public CallInfo findAllByStaff(User Staff) {
        return callInfoRepository.findCallInfoByStaff(Staff);
    }
}
