package com.example.gateservice.service.Impl;

import com.example.gateservice.model.Work;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.service.WorkService;
import org.springframework.stereotype.Service;

@Service
public class WorkServiceImpl extends BaseServiceImpl<Work> implements WorkService {
    @Override
    protected BaseRepository<Work> getRepository() {
        return null;
    }
}
