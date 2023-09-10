package com.example.gateservice.service.Impl;

import com.example.gateservice.model.Data;
import com.example.gateservice.util.DateUtil;
import com.example.gateservice.util.UploadFileUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Service
public class UploadFileServiceImpl {
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
    public static List<Data> getCustomersDataFromExcel(InputStream inputStream){
        List<Data> dataList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("data");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Data data = new Data();
                data.setDate(DateUtil.getCurrenDateHour());
                data.setDateChanged(DateUtil.getCurrenDateHour());
                data.setDateOnly(DateUtil.getCurrenDate());
                data.setDateChangedOnly(DateUtil.getCurrenDate());
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 1: data.setName(cell.getStringCellValue());
                        case 2: data.setPhone(cell.getStringCellValue());
                        case 3: data.setAddress(cell.getStringCellValue());
                    }
                    cellIndex++;
                }
                dataList.add(data);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return dataList;
    }
}
