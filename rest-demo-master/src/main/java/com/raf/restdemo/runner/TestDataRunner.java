package com.raf.restdemo.runner;

import com.raf.restdemo.domain.old.Admin;
import com.raf.restdemo.domain.old.Role;
import com.raf.restdemo.domain.old.UserRole;
import com.raf.restdemo.repository.newp.UserRepository;
import com.raf.restdemo.repository.old.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Profile({"default"})
@Component
public class TestDataRunner{
//
//    private AdminRepository adminRepository;
//
//    public TestDataRunner(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        //Insert roles
//        Admin admin = new Admin();
//        Role role = new Role("ROLE_ADMIN", "Admin role");
//        admin.setRole(role);
//        //Insert admin
//        admin.setEnabled(true);
//        admin.setFirstName("admin2");
//        admin.setLastName("admin2");
//        admin.setUsername("admin2");
//        admin.setPassword("admin2");
//        admin.setEmail("admin2@gmail.com");
//        admin.setPhoneNumber("123432443");
//        admin.setDateOfBirth(new Date(11-12-1998));
//        adminRepository.save(admin);
//    }
}
