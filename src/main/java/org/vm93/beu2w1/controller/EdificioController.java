package org.vm93.beu2w1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.beu2w1.model.Edificio;
import org.vm93.beu2w1.service.EdificioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/edificio")
public class EdificioController {
	
	@Autowired
	EdificioService edificioService;
	
	@GetMapping(path = "/all")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAllEdificio(Pageable pageable){
		return new ResponseEntity<>(edificioService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable Long id){
		return new ResponseEntity<>(edificioService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createEdificio(@RequestBody Edificio e){
		return new ResponseEntity<>(edificioService.salvaPostazione(e), HttpStatus.CREATED);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateEdificio(@RequestBody Edificio e){
		return new ResponseEntity<>(edificioService.aggiornaPostazione(e), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(edificioService.rimuoviUtente(id), HttpStatus.OK);
	}


}
