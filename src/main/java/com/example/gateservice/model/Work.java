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
@Table(name = "work")
public class Work extends BaseEntity{
    @Column(name = "timeint")
    private Long timeIn;

    @Column(name = "timeout")
    private Long timeOut;

    @Column(name = "total_order")
    private int totalOrder = 0;

    @Column(name = "success_order")
    private int successOrder = 0;

    @Column(name = "processed_order")
    private int processedOrder = 0;

    @Column(name = "only_order")
    private int onlyOrder = 0;

    @Column(name = "processed_only_order")
    private int processedOnlyOrder = 0;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "active")
    private int isActive = 1;

    @Column(name = "shopcode")
    private String shopCode;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private User staff;
}
