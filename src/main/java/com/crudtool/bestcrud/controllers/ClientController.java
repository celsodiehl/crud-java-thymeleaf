package com.crudtool.bestcrud.controllers;

import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.models.Product;
import com.crudtool.bestcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    //@Autowired
   //private ClientService clientService;

    @Autowired
    private ClientRepository repo;

    @GetMapping
    public String getClients(Model model){
        List<Client> clients = repo.findAll();
        model.addAttribute("clients", clients);
        return "clients/index";
    }
/*
  //  @GetMapping("/clients")
  //  public ResponseEntity<List<Client>> clients() {
        //ModelAndView andview = new ModelAndView("clients");
        //Iterable<Client> clientesIt = repo.findAll();
        //List<Client> clientsArray = new ArrayList<>();
        //List<Client> lista = this.clientService.findAll();
        //clients lá do HTML
       // andview.addObject("clients", lista);
       // return new ResponseEntity<>(lista, HttpStatus.OK);
   // }
/*
    @GetMapping("/findAll")
    public ResponseEntity<List<Client>> findAll() {
        try {
            List<Client> lista = this.clientService.findAll();
            // SE DEU TUDO CERTO
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            // SE DEU TUDO ERRADO
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Client client) {

        // TODA CLASSE CONTROLLER TEM QUE TER ESTRUTURA TRY CATCH
        try {
            String mensagem = this.clientService.save(client);
            // SE DEU TUDO CERTO
            return new ResponseEntity<String>(mensagem, HttpStatus.CREATED);
        } catch (Exception e) {
            // SE DEU TUDO ERRADO
            return new ResponseEntity<String>("Algo deu Errado ao Salvar", HttpStatus.BAD_REQUEST);
        }
    }*/

}
