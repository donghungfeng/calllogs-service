package com.example.gateservice.controller;

import com.example.gateservice.model.Work;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("work")
public class WorkController extends BaseController<Work>{
    @Autowired
    WorkService workService;
    @Override
    protected BaseService<Work> getService() {
        return workService;
    }
}
