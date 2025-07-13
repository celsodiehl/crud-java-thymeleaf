package com.crudtool.bestcrud.repositories;

import com.crudtool.bestcrud.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutcRepository extends JpaRepository<Product, Integer> {
}
