package com.example.gateservice.model;

import javax.persistence.*;

@Entity
@Table(name = "data")
public class Data extends BaseEntity{

    @Column(name = "phone")
    private String phone;

    @Column(name = "product")
    private String product;

    @Column(name = "product_value")
    private Long productValue;

    @Column(name = "status")
    private int status;

    @Column(name = "date")
    private Long date;

    @Column(name = "date_only")
    private Long dateOnly;

    @Column(name = "date_changed_only")
    private Long dateChangedOnly;

    @Column(name = "datechanged")
    private Long dateChanged;

    @Column(name = "price")
    private Double price;

    @Column(name = "address")
    private String address;

    @Column(name = "messager")
    private String messager;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
