package com.hamy.tradeshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hamy.tradeshop.model.Admin;
import com.hamy.tradeshop.service.AdminService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admins")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public List<Admin> getAdmins() {
        return adminService.getAllAdmins();
    }
    
    @PostMapping("/add")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Optional<Admin> admin = adminService.getAdminById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        Optional<Admin> adminOptional = adminService.getAdminById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            admin.setUsername(adminDetails.getUsername());
            admin.setPassword(adminDetails.getPassword());
            return ResponseEntity.ok(adminService.saveAdmin(admin));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
