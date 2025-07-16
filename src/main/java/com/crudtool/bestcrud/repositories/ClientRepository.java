package com.crudtool.bestcrud.repositories;

import com.crudtool.bestcrud.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
