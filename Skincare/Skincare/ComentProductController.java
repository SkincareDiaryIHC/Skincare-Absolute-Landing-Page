package com.skincare.backend.controllers;

import com.skincare.backend.entities.Cliente;
import com.skincare.backend.entities.ComentProduct;
import com.skincare.backend.repositories.ComentProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ComentProductController {
    @Autowired
    private ComentProductRepository comentProductRepository;
    @GetMapping("/comentproducts")
    public ResponseEntity<List<ComentProduct>> getAllComentProducts(){
        List<ComentProduct> comentProducts;
        comentProducts=comentProductRepository.findAll();
        for (ComentProduct coproduct: comentProducts) {
            coproduct.setProduct(null);
        }
        return new ResponseEntity<List<ComentProduct>>(comentProducts, HttpStatus.OK);
    }
    @PostMapping("/comentproducts")
    public ResponseEntity<ComentProduct> createCommentProduct(@RequestBody ComentProduct comentproduct) {
        ComentProduct newComentProduct = comentProductRepository.save(new ComentProduct(comentproduct.getValoracion(), comentproduct.getComentario(), comentproduct.getProduct()));
        return new ResponseEntity<ComentProduct>(newComentProduct, HttpStatus.CREATED);
    }

    @GetMapping("/comentproducts/{id}")
    public ResponseEntity<ComentProduct> getComentProductById(@PathVariable("id") Long id) {
        ComentProduct comentProduct = comentProductRepository.findById(id).get();
        comentProduct.setProduct(null);
        return new ResponseEntity<ComentProduct>(comentProduct, HttpStatus.OK);
    }
    @DeleteMapping("/comentproducts/{id}")
    public ResponseEntity<HttpStatus> deleteComentProductById(@PathVariable("id") Long id) {
        comentProductRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/comentproducts/{id}")
    public ResponseEntity<ComentProduct> updateComentProductById(@PathVariable("id") Long id, @RequestBody ComentProduct comentProduct)
    {

        ComentProduct foundComentProduct =  comentProductRepository.findById(id).get();

        if (comentProduct.getComentario()!=null)
            foundComentProduct.setComentario(comentProduct.getComentario());
        if (comentProduct.getValoracion()!=null)
            foundComentProduct.setValoracion(comentProduct.getValoracion());

        ComentProduct updateComentProduct =comentProductRepository.save(foundComentProduct);
        updateComentProduct.setProduct(null);
        return new ResponseEntity<ComentProduct>(updateComentProduct,HttpStatus.OK);
    }

}

