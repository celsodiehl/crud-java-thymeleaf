package com.crudtool.bestcrud.repositories;

import com.crudtool.bestcrud.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

/*
        @Autowired
        private JdbcTemplate jdbcTemplate;

         public List<Client> getClientes(){
         var clients = new ArrayList<Client>();

         String sql = "SELECT * FROM clients ORDER BY id DESC";
         SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

         while (rows.next()){
             Client client = new Client();
             client.setId(rows.getInt("id"));
             client.setFirstname(rows.getString("firstname"));
             client.setLastname(rows.getString("lastname"));
             client.setEmail(rows.getString("email"));
             client.setPhone(rows.getString("phone"));
             client.setAddress(rows.getString("address"));
             client.setCreatedAt(rows.getTimestamp("created_at"));
             clients.add(client);
         }
         return clients;

    }

      public List<Client> findByEmail(String email){
             return null;
      }*/


}
