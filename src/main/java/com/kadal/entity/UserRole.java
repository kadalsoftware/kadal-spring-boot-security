package com.kadal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Long role_id;
}
