//CONSULTORA CONTROLLER//

package com.skincare.backend.controllers;

import com.skincare.backend.entities.Cliente;
import com.skincare.backend.entities.Consultora;
import com.skincare.backend.entities.Login;
import com.skincare.backend.repositories.ConsultoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConsultoraController {
    @Autowired
    private ConsultoraRepository consultoraRepository;

    @GetMapping("/consultoras")
    public ResponseEntity<List<Consultora>> getAllConsultora(){
        List<Consultora> consultoras;
        consultoras=consultoraRepository.findAll();
        for (Consultora consul: consultoras) {
            consul.setLoginConsultora(null);
            consul.setComentConsultoras(null);
        }
        return new ResponseEntity<List<Consultora>>(consultoras, HttpStatus.OK);
    }

    @GetMapping("/consultoras/logins")
    public ResponseEntity<List<Consultora>> getAllConsultorasAndLogins() {
        List<Consultora> consultoras = clienteRepository.findAll();
        for (Consultora c : consultoras) {
            c.getLoginConsultora().setConsultora(null);
        }
        return new ResponseEntity<List<Consultora>>(consultoras, HttpStatus.OK);
    }

    @GetMapping("/consultoras/{id}")
    public ResponseEntity<Consultora> getConsultoraById(@PathVariable("id") Long id){
        Consultora consultora=consultoraRepository.findById(id).get();
        consultora.setLoginConsultora(null);
        consultora.setComentConsultoras(null);
        return new ResponseEntity<Consultora>(consultora,HttpStatus.OK);
    }

    @PostMapping("/consultoras")
    public ResponseEntity<Consultora> createConsultora(@RequestBody Consultora consultora) {
        Consultora newConsultora =  consultoraRepository.save(new Consultora(consultora.getNombre(),consultora.getApellido(),consultora.getDescripcion(),consultora.getEmpresa(),consultora.getContacto(),consultora.getCelular(),consultora.getLoginConsultora()));
        return new ResponseEntity<Consultora>(newConsultora,HttpStatus.CREATED);
    }

    @PutMapping("/consultoras/{id}")
    public ResponseEntity<Consultora> updateConsultoraById(@PathVariable("id") Long id, @RequestBody Consultora consultora)
    {

        Consultora foundConsultora =  consultoraRepository.findById(id).get();

        if (consultora.getNombre()!=null)
            foundConsultora.setNombre(consultora.getNombre());
        if (consultora.getApellido()!=null)
            foundConsultora.setApellido(consultora.getApellido());
        if (consultora.getDescripcion()!=null)
            foundConsultora.setDescripcion(consultora.getDescripcion());
        if (consultora.getEmpresa()!=null)
            foundConsultora.setEmpresa(consultora.getEmpresa());
        if (consultora.getContacto()!=null)
            foundConsultora.setContacto(consultora.getContacto());
        if (consultora.getCelular()!=null)
            foundConsultora.setCelular(consultora.getCelular());

        Consultora updateConsultora =consultoraRepository.save(foundConsultora);
        updateConsultora.setLoginConsultora(null);
        updateConsultora.setComentConsultoras(null);
        return new ResponseEntity<Consultora>(updateConsultora,HttpStatus.OK);
    }

    @DeleteMapping("/consultoras/{id}")
    public ResponseEntity<HttpStatus> deleteConsultoraById(@PathVariable("id") Long id) {
        consultoraRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
