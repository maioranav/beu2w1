package org.vm93.beu2w1.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vm93.beu2w1.auth.entity.ERole;
import org.vm93.beu2w1.auth.repository.RoleRepository;
import org.vm93.beu2w1.auth.security.JwtTokenProvider;
import org.vm93.beu2w1.model.Prenotazione;
import org.vm93.beu2w1.model.Utente;
import org.vm93.beu2w1.service.PrenotazioneService;
import org.vm93.beu2w1.service.UtenteService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/prenota")
public class PrenotazioneController {
	
	@Autowired
	PrenotazioneService prenotaService;
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	RoleRepository rolerepo;
	
	@GetMapping(path = "/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAllPrenotazioni(){
		return new ResponseEntity<>(prenotaService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getAllPrenotazioniUtente(@RequestHeader("Authorization") String token){
		String email = jwtTokenProvider.getUsername(token.substring(7, token.length()));
		Utente u = utenteService.findByEmail(email);
		return new ResponseEntity<>(prenotaService.findByUser(u), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> getById(@PathVariable Long id, @RequestHeader("Authorization") String token) throws Exception{
		String email = jwtTokenProvider.getUsername(token.substring(7, token.length()));
		Utente u = utenteService.findByEmail(email);
		Prenotazione p = prenotaService.findByID(id);
		if (!p.getUtente().equals(u) && !u.getRoles().contains(rolerepo.findByRoleName(ERole.ROLE_ADMIN).get())) {
			throw new Exception("Not your request!");
		}
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createPrenotazione(@RequestBody Prenotazione p){
		return new ResponseEntity<>(prenotaService.salvaPrenotazione(p), HttpStatus.CREATED);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updatePrenotazione(@RequestBody Prenotazione p){
		return new ResponseEntity<>(prenotaService.aggiornaPrenotazione(p), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
			return new ResponseEntity<>(prenotaService.rimuoviPrenotazione(id), HttpStatus.OK);
	}


}
