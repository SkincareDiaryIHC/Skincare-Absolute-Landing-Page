package com.skincare.backend.controllers;

import com.skincare.backend.entities.LoginConsultora;
import com.skincare.backend.repositories.LoginConsultoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LoginConsultoraController {
    @Autowired
    private LoginConsultoraRepository loginConsultoraRepository;

    @GetMapping("/loginconsultoras")
    public ResponseEntity<List<LoginConsultora>> getAllLoginConsultoras(){
        List<LoginConsultora> logins;
        logins=loginConsultoraRepository.findAll();
        if (logins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (LoginConsultora lo: logins) {
            lo.setConsultoras(null);
        }
        return new ResponseEntity<List<LoginConsultora>>(logins, HttpStatus.OK);
    }

    @PostMapping("/loginconsultoras")
    public ResponseEntity<LoginConsultora> createLoginConsultora(@RequestBody LoginConsultora logincon){
        LoginConsultora newLoginConsultora = loginConsultoraRepository.save(
                new LoginConsultora(logincon.getEmail(),
                        logincon.getPassword()
                ));

        return new ResponseEntity<LoginConsultora>(newLoginConsultora, HttpStatus.CREATED);
    }

    @GetMapping("/loginconsultoras/{id}")
    public ResponseEntity<LoginConsultora> getLoginConsultoraById(@PathVariable("id") Long id){
        LoginConsultora logincon=loginConsultoraRepository.findById(id).get();
        logincon.setConsultoras(null);
        return new ResponseEntity<LoginConsultora>(logincon,HttpStatus.OK);
    }
/*
    @GetMapping("/loginconsultoras/password/{password}")
    public ResponseEntity<LoginConsultora> getLoginByPassword(@PathVariable("password") String password){
        LoginConsultora logincon=loginRepository.findByPassword(password);
        logincon.setConsultoras(null);
        return new ResponseEntity<LoginConsultora>(logincon,HttpStatus.OK);
    }*/

    @PutMapping("/loginconsultoras/{id}")
    public ResponseEntity<LoginConsultora> updateLoginConsultoraById(@PathVariable("id") Long id, @RequestBody LoginConsultora logincon) {

        LoginConsultora updateLoginConsultora =  loginConsultoraRepository.findById(id).get();

        if (logincon.getEmail()!=null)
            updateLoginConsultora.setEmail(logincon.getEmail());
        if (logincon.getPassword()!=null)
            updateLoginConsultora.setPassword(logincon.getPassword());
        LoginConsultora updatedLoginConsultora =loginConsultoraRepository.save(updateLoginConsultora);
        updatedLoginConsultora.setConsultoras(null);
        return new ResponseEntity<LoginConsultora>(updatedLoginConsultora,HttpStatus.OK);

    }

    @DeleteMapping("/loginconsultoras/{id}")
    public ResponseEntity<HttpStatus> deleteLoginConsultoraById(@PathVariable("id") Long id){
        loginConsultoraRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}