package com.example.gateservice.service.Impl;

import com.example.gateservice.model.Data;
import com.example.gateservice.repository.BaseRepository;
import com.example.gateservice.repository.DataRepository;
import com.example.gateservice.service.DataService;
import com.example.gateservice.util.UploadFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DataServiceImpl extends BaseServiceImpl<Data> implements DataService {
    @Autowired
    DataRepository dataRepository;
    @Override
    protected BaseRepository<Data> getRepository() {
        return dataRepository;
    }

    @Override
    public void saveCustomersToDatabase(MultipartFile file){
        if(UploadFileServiceImpl.isValidExcelFile(file)){
            try {
                List<Data> customers = UploadFileServiceImpl.getCustomersDataFromExcel(file.getInputStream());
                this.dataRepository.saveAll(customers);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }
}
