package com.rest.crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rest.crud.crud.entity.Cidadao;
import com.rest.crud.crud.repository.CidadaoRepository;

@RestController
@RequestMapping("/cidadao")
public class CidadaoController {

	
	@Autowired
	CidadaoRepository repositorio;
	
	@GetMapping
	public ResponseEntity<List<Cidadao>> getCidadoes() {
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping(path = {"/{id}"})
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
	public ResponseEntity findById(@PathVariable Integer id){
	   return repositorio.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(value=HttpStatus.OK, reason="Sucesso!")
	public Cidadao criar(@RequestBody Cidadao cidadao){
	   return repositorio.save(cidadao);
	}
	
	@PutMapping(value="/{id}")
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
	public ResponseEntity atualizar(@PathVariable("id") Integer id,
	                                      @RequestBody Cidadao cidadao) {
	   return repositorio.findById(id)
	           .map(record -> {
	               record.setNome(cidadao.getNome());
	               record.setSobrenome(cidadao.getSobrenome());
	               record.setEmail(cidadao.getEmail());
	               record.setTelefone(cidadao.getTelefone());
	               Cidadao updated = repositorio.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Id não encontrado")
	public ResponseEntity<?> apagar(@PathVariable Integer id) {
	   return repositorio.findById(id)
	           .map(record -> {
	               repositorio.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
}