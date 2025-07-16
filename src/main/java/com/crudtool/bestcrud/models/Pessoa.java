package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Table(name = "pessoa")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {  //ELE IMPLEMETOU SERIALIZABLE

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private  String sobrenome;
    private int idade;

}
