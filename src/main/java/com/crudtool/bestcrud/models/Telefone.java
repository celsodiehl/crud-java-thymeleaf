package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_pessoa"))
    private Pessoa pessoa;

}
