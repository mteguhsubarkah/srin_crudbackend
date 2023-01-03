package com.srin.crud.controller;

import java.util.List;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srin.crud.domain.SrinUser;
import com.srin.crud.repository.SrinUserRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {

    @Autowired SrinUserRepository SrinUserRepository;
    
    //Get All
    @GetMapping("/users")
    public ResponseEntity<List<SrinUser>> getDailyReport (){
        List<SrinUser> SrinUsers = SrinUserRepository.findAll();
        return ResponseEntity.ok(SrinUsers);
    }

    //create
    @PostMapping("/create-user")
    public ResponseEntity<SrinUser> createUser(@RequestBody SrinUser user) {
        SrinUserRepository.save(user);
        return ResponseEntity.ok(user);
    }

    //delete
    @PostMapping("/delete-user")
    public ResponseEntity<SrinUser> deleteUser(@RequestParam Integer id) {
        SrinUser SrinUser = SrinUserRepository.findById(id).orElseThrow();
        SrinUserRepository.delete(SrinUser);
        return ResponseEntity.ok(SrinUser);
    }

    //update
    @PostMapping("/update-user")
  public ResponseEntity<SrinUser> updateUser(@RequestBody SrinUser user) {
        SrinUser srinUser = SrinUserRepository.findById(user.getId()).orElseThrow();
        srinUser.setFirstName(user.getFirstName());
        srinUser.setLastName(user.getLastName());
        srinUser.setEmail(user.getEmail());
        SrinUserRepository.save(user);
        return ResponseEntity.ok(user);
  }


}
