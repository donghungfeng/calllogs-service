package com.example.gateservice.service.Impl;

import com.example.gateservice.model.CallLogs;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.CallLogsRepository;
import com.example.gateservice.service.CalllLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalllLogsServiceImpl extends BaseServiceImpl<CallLogs> implements CalllLogsService {
    @Autowired
    CallLogsRepository callLogsRepository;
    @Override
    protected BaseRepository<CallLogs> getRepository() {
        return callLogsRepository;
    }
}
