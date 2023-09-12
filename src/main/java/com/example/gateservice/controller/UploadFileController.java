package com.example.gateservice.controller;

import com.example.gateservice.service.DataService;
import com.example.gateservice.service.Impl.UploadFileServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("uploadFile")
@CrossOrigin
public class UploadFileController{
    @Autowired
    UploadFileServiceImpl uploadService;

    @Autowired
    DataService dataService;

    @PostMapping("/data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file, @RequestParam("departmentId") Long departmentId){
        this.dataService.saveCustomersToDatabase(file, departmentId);
        return ResponseEntity
                .ok(Map.of("Message" , " data data uploaded and saved to database successfully"));
    }
}
