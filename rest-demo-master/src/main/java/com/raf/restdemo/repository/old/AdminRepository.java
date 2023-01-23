package com.raf.restdemo.repository.old;

import com.raf.restdemo.domain.old.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByUsernameAndPassword(String username, String password);
    Optional<Admin> findAdminByEmail(String email);
    Optional<Admin> findAdminByUsername(String username);
}
