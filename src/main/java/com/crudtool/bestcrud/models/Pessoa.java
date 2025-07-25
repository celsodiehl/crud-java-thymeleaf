package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é Obrigatório!")
    private String nome;
    @NotBlank(message = "Sobrenome é Obrigatório!")
    private  String sobrenome;
    private int idade;

    @OneToMany(mappedBy = "pessoa")
    private List<Telefone> telefones;

}
