package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.models.Pessoa;
import com.crudtool.bestcrud.repositories.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository repo;

    //READ
    @GetMapping
    public String getClients(Model model){
        List<Client> clients = repo.findAll();
        model.addAttribute("objs", clients);
        return "clients/index";
    }
    //ENTRA NO CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        return "clients/create";
    }

    //CREATE
    @PostMapping("/create")
    public String create(@Valid Client client, BindingResult result, Model model) {
        Date createdAt = new Date();
        if (result.hasErrors()) {
            return "clients/create";
        }
        client.setCreatedAt(createdAt);
        repo.save(client);
        return "redirect:/clients";
    }

    //UPDATE CARREGAR
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Client client = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Código de Cliente Inválido:" + id));

        model.addAttribute("obj", client);
        return "clients/edit";
    }

    //UPDATE ATUALIZAR
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Client client,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            client.setId(id);
            return "clients/edit";
        }

        repo.save(client);
        return "redirect:/clients";
    }

}
