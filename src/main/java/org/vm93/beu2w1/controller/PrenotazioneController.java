package org.vm93.beu2w1.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.service.PrenotazioneService;

@RestController
@RequestMapping("/api/prenota")
public class PrenotazioneController {
	
	@Autowired
	PrenotazioneService prenotaService;
	
	@GetMapping(path = "/all")
	public ResponseEntity<?> getAllPrenotazioni(){
		return new ResponseEntity<>(prenotaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
		return new ResponseEntity<>(prenotaService.findByID(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createPrenotazione(@RequestBody Prenotazione p){
		return new ResponseEntity<>(prenotaService.salvaPrenotazione(p), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updatePrenotazione(@RequestBody Prenotazione p){
		return new ResponseEntity<>(prenotaService.aggiornaPrenotazione(p), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(prenotaService.rimuoviPrenotazione(id), HttpStatus.OK);
	}


}
