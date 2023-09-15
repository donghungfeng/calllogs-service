package com.example.gateservice.controller;

import com.example.gateservice.model.CallLogs;
import com.example.gateservice.reponse.BaseResponse;
import com.example.gateservice.request.calllogs.CallLogsRequest;
import com.example.gateservice.service.BaseService;
import com.example.gateservice.service.CalllLogsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("callLogs")
@CrossOrigin
public class CallLogsController extends BaseController<CallLogs>{
    @Autowired
    CalllLogsService callLogsService;
    @Override
    protected BaseService<CallLogs> getService() {
        return callLogsService;
    }

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public BaseResponse create(@RequestBody CallLogsRequest body) throws NoSuchAlgorithmException, IOException {
        LocalDateTime date = body.getCalldate().toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        CallLogs callLogs = modelMapper.map(body, CallLogs.class);
        Long callDate = Long.parseLong(date.format(formatter));
        callLogs.setCalldate(callDate);
        return new BaseResponse().success(callLogsService.create(callLogs));
    }
}
