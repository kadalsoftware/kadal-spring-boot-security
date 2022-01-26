package com.kadal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_permissions")
@Data
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long role_id;

    private Long permission_id;

}
