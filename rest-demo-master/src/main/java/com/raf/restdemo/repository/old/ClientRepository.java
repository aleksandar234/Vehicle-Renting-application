package com.raf.restdemo.repository.old;

import com.raf.restdemo.domain.old.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByUsernameAndPassword(String username, String password);
    Optional<Client> findClientByEmail(String email);
    Optional<Client> findClientByUsername(String username);
}
