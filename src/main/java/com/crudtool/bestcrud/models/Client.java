package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "clients")
@Setter
@Getter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;

    @Column(name = "createdAt", nullable = false)
    private Timestamp createdAt;
    //public Timestamp timestamp;


}
