package com.crudtool.bestcrud.services;

import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@RestController
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /*
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> findAll() {
        try {
            List<Client> lista = this.clientRepository.findAll();
            // SE DEU TUDO CERTO
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            // SE DEU TUDO ERRADO
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }*/

    //CREATE
    /*
    public String save(Client client) {
       this.clientRepository.save(client);
        return client.getFirstname() + " Salvo com Sucesso!";
    }

    //READ
    public List<Client> findAll() {
        List<Client> lista = this.clientRepository.findAll();
        return lista;
   }

    //UPDATE
    public String update(Client client, Integer id) {
        client.setId(id);
        this.clientRepository.save(client);
        return "Cliente Atualizado com Sucesso!";
    }

    //DELETE
    public String delete(Integer id) {
        this.clientRepository.deleteById(id);
        return "Cliente Deletado com Sucesso!";
    }

    public Client findById(Integer id) {
        Optional<Client> client = this.clientRepository.findById(id);
        return client.get();
    }

    public List<Client> findByEmaL(String email) {
        return this.clientRepository.findByEmail(email);
    }*/

}
