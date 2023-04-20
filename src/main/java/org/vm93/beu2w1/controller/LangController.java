package org.vm93.beu2w1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.vm93.beu2w1.model.Info;
import org.vm93.beu2w1.service.InfoService;

@Controller
@RequestMapping("/info")
public class LangController {

	@Autowired
	InfoService infodao;
	
	@GetMapping
	public @ResponseBody String getHomePage() {
		System.out.println("getHomePage");
		return "Select a language adding /LANG to your URL";
	}

	@GetMapping("/{lang}")
	public @ResponseBody String getHomePagePathParam(@PathVariable String lang) {
		List<Info> list = infodao.findByInitials(lang);

		if (list.size() > 0) {
			return list.get(0).getDescription();
		} else {
			return "Language not available.";
		}
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getAllInfo() {
		List<Info> list = infodao.findAll();
		return new ResponseEntity<>(list, HttpStatus.CREATED);
	}
	
	@PatchMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<?> update(@RequestBody Info upInfo) {
		String lang = upInfo.getLang();
		try {
			Info oldInfo = (Info) infodao.findByInitials(lang).get(0);
			oldInfo.setDescription(upInfo.getDescription());
			infodao.salvaInfo(oldInfo);
			return new ResponseEntity<>(upInfo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> register(@RequestBody Info newInfo) {
		try {
			infodao.salvaInfo(newInfo);
			return new ResponseEntity<>(newInfo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

}