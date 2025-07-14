package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table(name = "pessoa")
@Getter
public class Pessoa {  //ELE IMPLEMETOU SERIALIZABLE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private  String sobrenome;
    private int idade;

}
