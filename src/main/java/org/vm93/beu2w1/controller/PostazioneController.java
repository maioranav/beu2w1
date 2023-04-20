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
import org.vm93.beu2w1.model.Postazione;
import org.vm93.beu2w1.service.PostazioneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/postazione")
public class PostazioneController {
	
	@Autowired
	PostazioneService postService;
	
	@GetMapping(path = "/all")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAllPostazioni(Pageable pageable){
		return new ResponseEntity<>(postService.findAll(pageable), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable Long id){
		return new ResponseEntity<>(postService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createPostazione(@RequestBody Postazione p){
		return new ResponseEntity<>(postService.salvaPostazione(p), HttpStatus.CREATED);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updatePostazione(@RequestBody Postazione p){
		return new ResponseEntity<>(postService.aggiornaPostazione(p), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(postService.rimuoviUtente(id), HttpStatus.OK);
	}


}
