package com.example.gateservice.dtos;

import com.example.gateservice.model.Department;
import com.example.gateservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataDto {
    private Long id;
    private String name;
    private String phone;
    private String product;
    private Long productValue;
    private Long date;
    private Long dateOnly;
    private Long dateChangedOnly;
    private Long dateChanged;
    private String address;
    private String messager;
    private String note;
    private UserDto staff;
    private Department department;
}
