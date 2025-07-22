package com.crudtool.bestcrud.repositories;

import com.crudtool.bestcrud.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
/*
    @Query("SELECT p FROM Client p WHERE p.firstname LIKE %?1%")
    List<Client> findByName(String nome);

    //@Query(value = "SELECT * FROM clients p WHERE p.firstname LIKE %:keyword% OR p.lastname LIKE %:keyword%", nativeQuery = true)
    //List<Client> findByKeyword(@Param("keyword") String keyword);
List<Client> findByFirstnameContainingIgnoreCase(String firstname);
*/
    //@Query(value = "SELECT * FROM clients s WHERE s.firstname LIKE %:keyword% OR s.lastname LIKE %:keyword%",
    @Query(value = "SELECT * FROM clients s WHERE s.firstname LIKE %?1%", nativeQuery = true)
    List<Client> findByNomeContainingIgnoreCase(@Param("firstname") String firstname);


}
