package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Pessoa;
import com.crudtool.bestcrud.models.Product;
import com.crudtool.bestcrud.models.ProductDTO;
import com.crudtool.bestcrud.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

  /*
    @RequestMapping
    public ModelAndView inicio(){
      //RETORNAR OBJ VAZIO
        andView.addObject("obj", new Pessoa());
    }*/

     //CREATE
    @PostMapping("/create")
    public ModelAndView salvar(Pessoa pessoa){
        pessoaRepository.save(pessoa);

        ModelAndView andView = new ModelAndView("pessoas/index");
        Iterable<Pessoa> it = pessoaRepository.findAll();

                                     //pessoas é o objeto que vai para o HTML
        andView.addObject("pessoas", it);
        //RETORNAR OBJ VAZIO
        andView.addObject("obj", new Pessoa());
        return andView;
    }

    //ENTRA NO CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model){
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        return "pessoas/create";
    }

    //LISTAGEM
    @GetMapping             //esse /pessoa é da pasta pessoa = templates/pessoa
    public ModelAndView pessoas(){  //Ao Clicar volta pra tela de LISTAGEM = index
        ModelAndView andView = new ModelAndView("pessoas/index");
        Iterable<Pessoa> it = pessoaRepository.findAll();
                                     //pessoas é o objeto que vai para o HTML
        andView.addObject("pessoas", it);
        return andView;
    }

    //UPDATE CARREGAR
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Código de Pessoa Inválido:" + id));

        model.addAttribute("pessoas", pessoa);
        return "pessoas/edit";
    }

    //UPDATE ATUALIZAR
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Pessoa pessoa,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            pessoa.setId(id);
            return "pessoas/edit";
        }

        pessoaRepository.save(pessoa);
        return "redirect:/pessoas";
    }

    //DELETE
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")Long id) {
        pessoaRepository.deleteById(id);

        ModelAndView andView = new ModelAndView("redirect:/pessoas");
        andView.addObject("pessoas", pessoaRepository.findAll());
        //RETORNAR OBJ VAZIO
        andView.addObject("obj", new Pessoa());
        return andView;
    }

}
