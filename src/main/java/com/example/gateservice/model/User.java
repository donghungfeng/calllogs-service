package com.example.gateservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter@Builder
public class User extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "is_active")
    private int isActive = 1;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name ="password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;
}
