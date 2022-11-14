package com.skincare.backend.controllers;

import com.skincare.backend.entities.ComentConsultora;
import com.skincare.backend.entities.ComentProduct;
import com.skincare.backend.entities.Product;
import com.skincare.backend.repositories.ComentConsultoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComentConsultoraController {
        @Autowired
        private ComentConsultoraRepository comentConsultoraRepository;

        @GetMapping("/comentconsultoras")
        public ResponseEntity<List<ComentConsultora>> getAllComentConsultoras() {
                List<ComentConsultora> comentConsultoras;
                comentConsultoras = comentConsultoraRepository.findAll();
                for (ComentConsultora coconsul : comentConsultoras) {
                        coconsul.setConsultora(null);
                }
                return new ResponseEntity<List<ComentConsultora>>(comentConsultoras, HttpStatus.OK);
        }

        @PostMapping("/comentconsultoras")
        public ResponseEntity<ComentConsultora> createCommentConsultora(@RequestBody ComentConsultora comentconsultora) {
                ComentConsultora newComentConsultora = comentConsultoraRepository.save(new ComentConsultora(comentconsultora.getValoracion(), comentconsultora.getComentario(), comentconsultora.getConsultora()));
                return new ResponseEntity<ComentConsultora>(newComentConsultora, HttpStatus.CREATED);
        }

        @GetMapping("/comentconsultoras/{id}")
        public ResponseEntity<ComentConsultora> getComentconsultoraById(@PathVariable("id") Long id) {
                ComentConsultora comentConsultora = comentConsultoraRepository.findById(id).get();
                comentConsultora.setConsultora(null);
                return new ResponseEntity<ComentConsultora>(comentConsultora, HttpStatus.OK);
        }
        @DeleteMapping("/comentconsultoras/{id}")
        public ResponseEntity<HttpStatus> deleteComentconsultoraById(@PathVariable("id") Long id) {
                comentConsultoraRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @PutMapping("/comentconsultoras/{id}")
        public ResponseEntity<ComentConsultora> updateComentconsultoraById(@PathVariable("id") Long id, @RequestBody ComentConsultora comentConsultora) {

                ComentConsultora foundComentconsultora =  comentConsultoraRepository.findById(id).get();

                if (comentConsultora.getConsultora()!=null)
                        foundComentconsultora.setConsultora(comentConsultora.getConsultora());
                if (comentConsultora.getComentario()!=null)
                        foundComentconsultora.setComentario(comentConsultora.getComentario());
                if (comentConsultora.getValoracion()!=null)
                        foundComentconsultora.setValoracion(comentConsultora.getValoracion());

                ComentConsultora updateComentconsultora = comentConsultoraRepository.save(foundComentconsultora);
                updateComentconsultora.setConsultora(null);
                return new ResponseEntity<ComentConsultora>(updateComentconsultora, HttpStatus.OK);

        }


}
