package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Marca;
import com.crudtool.bestcrud.models.Pessoa;
import com.crudtool.bestcrud.repositories.MarcaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaRepository repo;

    //READ
    @GetMapping
    public String getObjs(Model model) {
        List<Marca> objs = repo.findAll();
        model.addAttribute("objs", objs);
        return "marcas/index";
    }


    //ENTRA NO CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        Marca obj = new Marca();
        model.addAttribute("obj", obj);
        return "marcas/create";
    }

    //CREATE
    @PostMapping("/create")
    public String create(@Valid Marca obj, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "marcas/create";
        }
        repo.save(obj);
        model.addAttribute("mensagemSucesso", "Marca cadastrado com sucesso!");
        return "redirect:/marcas";
    }

    //UPDATE CARREGAR
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Marca objs = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Código de Marca Inválido:" + id));
        //Esse objs envia p/ th:object="${objs}">
        model.addAttribute("objs", objs);
        return "marcas/edit";
    }

    //UPDATE ATUALIZAR
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Marca obj,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            obj.setId(id);
            return "pessoas/edit";
        }

        repo.save(obj);
        return "redirect:/marcas";
    }

    //DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Marca obj = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Código Inválido:" + id));
        repo.delete(obj);
        return "redirect:/marcas";
    }

}
