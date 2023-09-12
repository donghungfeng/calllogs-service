package com.example.gateservice.controller;

import com.example.gateservice.model.Data;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.request.data.AssignDataRequest;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("data")
@CrossOrigin
public class DataController extends BaseController<Data> {
    @Autowired
    DataService dataService;
    @Override
    protected BaseService<Data> getService() {
        return dataService;
    }

    @PostMapping("assignData")
    public BaseResponse assignData(@RequestBody AssignDataRequest assygnDataRequest) throws NoSuchAlgorithmException {
        return new BaseResponse().success(dataService.assignData(assygnDataRequest));
    }
}
