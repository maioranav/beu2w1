package org.vm93.beu2w1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.service.UtenteService;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {
	
	@Autowired
	UtenteService utenteService;
	
	@GetMapping(path = "/all")
	public ResponseEntity<?> getAllUsers(Pageable pageable){
		return new ResponseEntity<>(utenteService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		return new ResponseEntity<>(utenteService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody Utente u){
		return new ResponseEntity<>(utenteService.salvaUtente(u), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody Utente u){
		return new ResponseEntity<>(utenteService.aggiornaUtente(u), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(utenteService.rimuoviUtente(id), HttpStatus.OK);
	}


}
