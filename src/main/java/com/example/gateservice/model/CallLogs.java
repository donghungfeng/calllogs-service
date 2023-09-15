package com.example.gateservice.model;

import com.example.gateservice.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "call_logs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CallLogs extends BaseEntity {
    @Column(name = "callid")
    private String callid;

    @Column(name = "calldate")
    @JsonFormat(pattern="yyyyMMddHHMMss")
    private Long calldate;

    @Column(name = "extension")
    private String extension;

    @Column(name = "phone")
    private String phone;

    @Column(name = "duration")
    private String duration ;

    @Column(name = "billsec")
    private String billsec;

    @Column(name = "status")
    private String status;

    @Column(name = "recording")
    private String recording;

    @Column(name = "blacklist")
    private String blacklist;
}