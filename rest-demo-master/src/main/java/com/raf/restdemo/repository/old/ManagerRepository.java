package com.raf.restdemo.repository.old;

import com.raf.restdemo.domain.old.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findManagerByUsernameAndPassword(String username, String password);
    Optional<Manager> findManagerByEmail(String email);
    Optional<Manager> findManagerByUsername(String username);

}
