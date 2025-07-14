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

    //INJETANDO CLASSE REPOSITORY
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

    //CREATE E IMAGEM
    @PostMapping("/create")
    public String cadastrar(@Valid ProductDTO productDTO, BindingResult result) {

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

    @GetMapping("/edit")
    public String showEditPage( Model model, @RequestParam int id){
        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            ProductDTO productDTO= new ProductDTO();
            productDTO.setName(product.getName());
            productDTO.setBrand(product.getBrand());
            productDTO.setCategory(product.getCategory());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            model.addAttribute("productDto", productDTO);

        }catch (Exception e){
            return "redirect:/products";
        }
        return "products/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model, @RequestParam int id, @Valid @ModelAttribute ProductDTO productDTO, BindingResult result){

        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if(result.hasErrors()){
                return "products/edit";
            }

            if(!productDTO.getImageFile().isEmpty()){
                //DELETA IMAGE ANTIGA
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                }catch (Exception e){

                }
                //SALVA NOVA IMAGEM
                MultipartFile image = productDTO.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime()+ "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }

            product.setName(productDTO.getName());
            product.setBrand(productDTO.getBrand());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            repo.save(product);
        }catch (Exception e){

        }
        return "redirect:/products";
    }

    //DELETE
    @GetMapping("/delete")
    public String delete(@RequestParam int id) {

        Product product = null;
        try {
            product = repo.findById(id).get();

            //DELETA IMAGEM
            Path imagePath = Paths.get("public/images/" + product.getImageFileName());

            try {
                Files.delete(imagePath);
            } catch (Exception e) {
                System.out.println("Erro ao Deletar Imagem.: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro.: " + e.getMessage());
        }
        repo.delete(product);

        return "redirect:/products";
    }

}
