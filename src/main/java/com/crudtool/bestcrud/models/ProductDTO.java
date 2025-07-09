package com.crudtool.bestcrud.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ProductDTO {

    @NotEmpty(message = "Um Nome é requerido")
    private String name;

    @NotEmpty(message = "Uma Marca é requerido")
    private String brand;

    @NotEmpty(message = "Um Nome é requerido")
    private String category;

    @Min(0)
    private double price;

    @Size(min = 10, message = "Descrição deve conter no minimo de 10 caracteres")
    @Size(min = 2000, message = "Descrição não deve exceder 2000 caracteres")
    private String description;

    private MultipartFile imageFile;

}