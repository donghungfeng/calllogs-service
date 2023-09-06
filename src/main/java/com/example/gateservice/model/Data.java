package com.example.gateservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "data")
public class Data extends BaseEntity{

    @Column(name = "phone")
    private String phone;

    @Column(name = "street")
    private String street;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "product")
    private String product;

    @Column(name = "status")
    private int status;

    @Column(name = "date")
    private Long date;

    @Column(name = "date_only")
    private Long dateOnly;

    @Column(name = "date_changed_only")
    private Long dateChangedOnly;

    @Column(name = "link")
    private String link;

    @Column(name = "ipaddress")
    private String ipAddress;

    @Column(name = "datechanged")
    private Long dateChanged;

    @Column(name = "utm_source")
    private String utm_source;

    @Column(name = "utm_medium")
    private String utm_medium;

    @Column(name = "utm_campaign")
    private String utm_campation;

    @Column(name = "utm_term")
    private String utm_term;

    @Column(name = "utm_content")
    private String utm_content;

    @Column(name = "variant_url ")
    private String variant_url;

    @Column(name = "price")
    private Double price;

    @Column(name = "message")
    private String message;

    @Column(name = "total_product_value")
    private Long totalProductValue;

    @Column(name = "note")
    private String note;

    @Column(name = "shopcode")
    private String shopCode;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "COGS")
    private Long cogs;

    @Column(name = "product_ids")
    private String productIds;

    @Column(name = "delivery_fee")
    private Long deliveryFee;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "note_shippingGHSV")
    private String noteShipping;

    @Column(name = "shipping_order_code")
    private String shippingCode;

    @Column(name = "time_order_success")
    private Long timeOrderSuccess;

    @Column(name = "data_info")
    private String dataInfo;

    @Column(name = "shipping_status")
    private String shippingStatus;

    @Column(name = "time_shipping")
    private Long timeShipping;

    @Column(name = "last_update_tracking_time")
    private Long lastUpdateTrackingTime;

    @Column(name = "time_financed")
    private Long timeFinanced;

    @Column(name = "actual_fee")
    private Long actualFee;
}
