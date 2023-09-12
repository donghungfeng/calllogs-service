package com.example.gateservice.service;

import com.example.gateservice.model.Work;

public interface WorkService extends BaseService<Work>{
    public Work checkIn(String jwt);
    public Work checkOut(String jwt);
    public Work checkActive(String jwt);
}
