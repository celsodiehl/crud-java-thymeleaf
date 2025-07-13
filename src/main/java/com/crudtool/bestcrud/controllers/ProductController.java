package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Product;
import com.crudtool.bestcrud.models.ProductDTO;
import com.crudtool.bestcrud.repositories.ProdutcRepository;
import com.crudtool.bestcrud.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService pService;

    //INJETANDO CKASSE REPOSITORY
    @Autowired
    private ProdutcRepository repo;

    //LISTAGEM
    @GetMapping
    public String showProductList(Model model){
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

    //CREATE usa para ir no CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDto", productDTO);
        return "products/create";
    }

    //OBRIGANDO ENVIAR IMAGEM
    @PostMapping
    public String createProduct(
            @Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {

        if(productDTO.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "Imagem é Requerida"));
        }

        if(result.hasErrors()){
            return "products/create";
        }

        //SALVAR IMAGEM NA PASTA
        MultipartFile image = productDTO.getImageFile();
        Date createdAt = new Date();
        String storeFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try{
            String uploadDir = "public/images";
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storeFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (Exception e){
            System.out.println("Erro.:" + e.getMessage());
        }
        Product p = new Product();
        p.setCreatedAt(new Date());
        repo.save(p);
        return "redirect:/products";
    }


    /*
    @PostMapping
    public String inserir(@Valid @ModelAttribute ProductDTO productDto, BindingResult result) {
        productDto.setCreatedAt(new Date());

        if (productDto.getImageFile() != null && !productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "Imagem é Requerida"));
        }

        if (result.hasErrors()) {
            return "products/create";
        }

        //SALVAR IMAGEM NA PASTA
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storeFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storeFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
            System.out.println("Erro.:" + e.getMessage());
        }
        ProductDTO pNovo = new ProductDTO();
        //repo.save(pNovo);
        //return pNovo;
    }*/

    //BACKUP
    //ESTA FAZENDO ESSE
    @PostMapping("/create")
    public String cadastrar(@Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {

        if(productDTO.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "Imagem é Requerida"));
        }

        if(result.hasErrors()){
            return "products/create";
        }

        //SALVAR IMAGEM NA PASTA
        MultipartFile image = productDTO.getImageFile();
        Date createdAt = new Date();
        String storeFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try{
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if(!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storeFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (Exception e){
            System.out.println("Erro.:" + e.getMessage());
        }

        Product prod = new Product();
        prod.setName(productDTO.getName());
        prod.setCategory(productDTO.getCategory());
        prod.setBrand(productDTO.getBrand());
        prod.setPrice(productDTO.getPrice());
        prod.setDescription(productDTO.getDescription());
        prod.setCreatedAt(createdAt);
        prod.setImageFileName(storeFileName);
        repo.save(prod);
        return "redirect:/products";
    }

    /*
       //EU ESTAVA USANDO ESSE
    @PostMapping("/create")
    public Product inserir(Product produto) {
        produto.setCreatedAt(new Date());
        Product pNovo = repo.saveAndFlush(produto);
        //return pNovo;
        return pNovo;
    }
     */

}
