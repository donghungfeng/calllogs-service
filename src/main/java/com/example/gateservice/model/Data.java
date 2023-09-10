package com.example.gateservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data")
public class Data extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "product")
    private String product;

    @Column(name = "product_value")
    private Long productValue;

    @Column(name = "date")
    private Long date;

    @Column(name = "date_only")
    private Long dateOnly;

    @Column(name = "date_changed_only")
    private Long dateChangedOnly;

    @Column(name = "datechanged")
    private Long dateChanged;

    @Column(name = "address")
    private String address;

    @Column(name = "messager")
    private String messager;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
