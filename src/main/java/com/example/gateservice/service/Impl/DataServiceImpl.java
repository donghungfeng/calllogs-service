package com.example.gateservice.service.Impl;

import com.example.gateservice.model.Data;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.DataRepository;
import com.example.gateservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl extends BaseServiceImpl<Data> implements DataService {
    @Autowired
    DataRepository dataRepository;
    @Override
    protected BaseRepository<Data> getRepository() {
        return dataRepository;
    }
}
