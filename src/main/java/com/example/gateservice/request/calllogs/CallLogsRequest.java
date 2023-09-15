package com.example.gateservice.request.calllogs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallLogsRequest {
    private String callid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date calldate;
    private String extension;
    private String phone;
    private String duration ;
    private String billsec;
    private String status;
    private String recording;
    private String blacklist;
}
