package com.crudtool.bestcrud.controllers;

import ch.qos.logback.classic.Logger;
import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.repositories.ClientRepository;
import com.crudtool.bestcrud.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    private ClientRepository repo;

    @Autowired
    private ClientService service;

    //READ
    @GetMapping
    public String getClients(Model model) {
        List<Client> clients = repo.findAll();
        model.addAttribute("objs", clients);
        return "clients/index";
    }

    @PostMapping("/listar")
    public String listarClientes(@RequestParam(name = "filtro", required = false) String filtro, Model model) {
        List<Client> clients;

        if (filtro != null && !filtro.isEmpty()) {
            clients = repo.findByNomeContainingIgnoreCase(filtro);
        } else {
            clients = repo.findAll();
        }

        model.addAttribute("objs", clients);
        model.addAttribute("filtro", filtro); // mantém o valor no input
        return "clients";
    }

    //ENTRA NO CREATE
    @GetMapping("/create")
    public String showCreatePage(Model model) {
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
        log.info("Cadastrado Com Sucesso!");
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

    //DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Client client = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Código Inválido:" + id));
        repo.delete(client);
        return "redirect:/clients";
    }

    @GetMapping("/buscar")
    public String listar(Model model, @RequestParam(required = false) String firstname) {
        if (firstname != null && !firstname.isEmpty()) {
            List<Client> clients = repo.findByNomeContainingIgnoreCase(firstname);
            model.addAttribute("clients", clients);
        } else {
            List<Client> clients = repo.findByNomeContainingIgnoreCase(firstname);
            model.addAttribute("clients", clients);
        }
        return "redirect:/clients";
    }

    //KEYWORDS TESTE
    /*
    @GetMapping("/filtrar")
    public String filtrar(@RequestParam("filtro") String filtro, Model model) {
        List<Client> itensFiltrados = repo.findByName(filtro);
        model.addAttribute("objs", itensFiltrados);
        model.addAttribute("filtro", filtro); // Passa o filtro para o template
        return "redirect:/clients";
    }

    @GetMapping("/clients")
    public String getProduct(Model model,
                             @ModelAttribute("myFormObject") Client obj, BindingResult result) {
        List<Client> clients = this.service.filtrarItensPorNome(obj.getFirstname());
        model.addAttribute("objs", clients);
        return "redirect:/clients";
    }

    @GetMapping({"/", "/search"})
    public String home(Client shop, Model model, String keyword) {
        if (keyword != null) {
            List<Client> list = service.getByKeyword(keyword);
            model.addAttribute("objs", list);
        } else {
            List<Client> list = service.getAllShops();
            model.addAttribute("objs", list);
        }
        return "redirect:/clients";
    }*/

}
