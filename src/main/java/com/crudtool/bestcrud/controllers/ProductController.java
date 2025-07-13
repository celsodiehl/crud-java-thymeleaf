package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.models.Product;
import com.crudtool.bestcrud.models.ProductDTO;
import com.crudtool.bestcrud.repositories.ProdutcRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProdutcRepository repo;

    @GetMapping
    public String showProductList(Model model){
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDto", productDTO);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {

        if(productDTO.getImageFile() != null && !productDTO.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "Imagem é Requerida"));
        }

        if(result.hasErrors()){
            return "products/CreateProduct";
        }
        return "redirect/products";
    }

}
