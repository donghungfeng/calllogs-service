package com.example.gateservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "deparmenrt")
public class Department extends BaseEntity{
    @Column(name = "status")
    private int status;
}
