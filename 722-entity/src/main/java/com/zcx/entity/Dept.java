package com.zcx.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
