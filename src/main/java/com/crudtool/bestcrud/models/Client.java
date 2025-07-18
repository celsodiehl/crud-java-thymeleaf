package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "clients")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome é Obrigatório")
    private String firstname;
    @NotBlank(message = "Sobrenome é Obrigatório")
    private String lastname;
    @NotBlank(message = "CPF é Obrigatório")
    private String cpf;
    private String email;
    private String phone;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "createdAt")
    private Date createdAt;


}
