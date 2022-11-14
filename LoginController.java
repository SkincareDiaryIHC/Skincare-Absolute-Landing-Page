package com.skincare.backend.controllers;

import com.skincare.backend.entities.Login;
import com.skincare.backend.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/logins")
    public ResponseEntity<List<Login>> getAllLogins(){
        List<Login> logins;
        logins=loginRepository.findAll();
        if (logins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (Login l: logins) {
            l.setClientes(null);
        }
        return new ResponseEntity<List<Login>>(logins, HttpStatus.OK);
    }

    @PostMapping("/logins")
    public ResponseEntity<Login> createLogin(@RequestBody Login login){
        Login newLogin = loginRepository.save(
                new Login(login.getEmail(),
                        login.getPassword()
                ));

        return new ResponseEntity<Login>(newLogin, HttpStatus.CREATED);
    }

    @GetMapping("/logins/{id}")
    public ResponseEntity<Login> getLoginById(@PathVariable("id") Long id){
        Login login=loginRepository.findById(id).get();
        login.setClientes(null);
        return new ResponseEntity<Login>(login,HttpStatus.OK);
    }
/*
    @GetMapping("/logins/password/{password}")
    public ResponseEntity<Login> getLoginByPassword(@PathVariable("password") String password){
        Login login=loginRepository.findByPassword(password);
        login.setClientes(null);
        return new ResponseEntity<Login>(login,HttpStatus.OK);
    }*/

    @PutMapping("/logins/{id}")
    public ResponseEntity<Login> updateLoginById(@PathVariable("id") Long id, @RequestBody Login login) {

        Login updateLogin =  loginRepository.findById(id).get();

        if (login.getEmail()!=null)
            updateLogin.setEmail(login.getEmail());
        if (login.getPassword()!=null)
            updateLogin.setPassword(login.getPassword());
        Login updatedLogin =loginRepository.save(updateLogin);
        updatedLogin.setClientes(null);
        return new ResponseEntity<Login>(updatedLogin,HttpStatus.OK);

    }


    @DeleteMapping("/logins/{id}")
    public ResponseEntity<HttpStatus> deleteLoginById(@PathVariable("id") Long id){
        loginRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
