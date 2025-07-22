package com.crudtool.bestcrud.services;

import com.crudtool.bestcrud.models.Client;
import com.crudtool.bestcrud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
@Service
public class ClientService {
/*
    @Autowired
    private ClientRepository repo;

    public List<Client> filtrarItensPorNome(String filtro) {
        List<Client> todosItens = repo.findAll(); // Ou de onde você busca os itens
        if (filtro == null || filtro.isEmpty()) {
            return todosItens;
        }
        return todosItens.stream()
                .filter(item -> item.getFirstname().toLowerCase().startsWith(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Client> getAllShops() {
        List<Client> list = repo.findAll();
        return list;
    }

    public List<Client> getByKeyword(String keyword) {
        return repo.findByKeyword(keyword);
    }
*/
}
