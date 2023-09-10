package com.example.gateservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleUser extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "is_active")
    private int isActive = 1;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "user_id", nullable = false)
    private Long userId;



}
