package com.zcx.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


}
