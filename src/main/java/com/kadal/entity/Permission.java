package com.kadal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permission;

}
