package com.example.gateservice.service;

import com.example.gateservice.model.Data;
import com.example.gateservice.request.data.AssignDataRequest;
import org.springframework.web.multipart.MultipartFile;

public interface DataService extends BaseService<Data>{
    public void saveCustomersToDatabase(MultipartFile file, Long departmentId);

    public String assignData(AssignDataRequest assygnDataRequest);
}
