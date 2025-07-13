package com.crudtool.bestcrud.services;

import com.crudtool.bestcrud.models.Product;
import com.crudtool.bestcrud.repositories.ProdutcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProdutcRepository prodRepository;

    // O Repository verifica se tem ID ou não e dai cria ou altera
    public Product inserir(Product produto) {
        Product pNovo = prodRepository.saveAndFlush(produto);
        return pNovo;
    }

}
