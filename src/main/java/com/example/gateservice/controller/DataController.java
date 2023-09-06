package com.example.gateservice.controller;

import com.example.gateservice.model.BaseEntity;
import com.example.gateservice.model.Data;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("data")
public class DataController extends BaseController<Data> {
    @Autowired
    DataService dataService;
    @Override
    protected BaseService<Data> getService() {
        return dataService;
    }
}
