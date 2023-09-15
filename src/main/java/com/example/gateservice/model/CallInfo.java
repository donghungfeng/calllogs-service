package com.example.gateservice.model;

import com.example.gateservice.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "call_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CallInfo extends BaseEntity {
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "uri")
    private String uri;

    @Column(name = "password")
    private String password;

    @Column(name = "socket")
    private String socket;

    @Column(name = "is_active")
    private int isActive = 1;

    @Column(name = "is_work")
    private int isWork;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "staff_id", nullable = true)
    private User staff;
}
