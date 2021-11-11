package com.prova.novembro.controller;

import com.prova.novembro.dto.ContatoDto;
import com.prova.novembro.form.ContatoForm;
import com.prova.novembro.service.ContatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public ResponseEntity<List<ContatoDto>> findAll(){
        return ResponseEntity.ok(contatoService.findAll());
    }
    @PostMapping
    public ResponseEntity<ContatoDto> create(@RequestBody @Valid ContatoForm contatoForm){
        return ResponseEntity.ok(contatoService.create(contatoForm));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDto> update(@PathVariable Long id, @RequestBody @Valid ContatoForm contatoForm){
        return ResponseEntity.ok(contatoService.update(id, contatoForm));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        contatoService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(contatoService.findById(id));
    }
}
