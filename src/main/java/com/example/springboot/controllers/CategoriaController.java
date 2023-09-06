package com.example.springboot.controllers;

import com.example.springboot.dtos.CategoriaRecordDTO;
import com.example.springboot.models.CategoriaModel;
import com.example.springboot.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping("/categoria")
    public ResponseEntity<CategoriaModel> saveCategoria(@RequestBody @Valid CategoriaRecordDTO categoriaRecordDTO){
        var categoriaModel = new CategoriaModel();
        BeanUtils.copyProperties(categoriaRecordDTO,categoriaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoriaModel));
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaModel>> getAllCategoria(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Object> deleteObject(@PathVariable(value = "id") UUID id){
        Optional<CategoriaModel> categoria0 = categoriaRepository.findById(id);
        if(categoria0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        categoriaRepository.delete(categoria0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada");

    }

}
