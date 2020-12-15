package com.example.restservice;

//import org.apache.log4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CopterController {
	//private final static Logger logger = Logger.getLogger(CopterController.class);

	private final MailService mailService = new MailService();

	@CrossOrigin(origins = "http://localhost:3000")
	//@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/add")
		public Email add(@RequestBody Email newEmail) {
			return mailService.add(newEmail);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/findByEmail")
		public ArrayList<Email> findByEmail(@RequestBody Email Obj) {
			return mailService.findByEmail(Obj);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/findBySubj")
	public ArrayList<Email> findBySubj(@RequestBody Email Obj) {
		return mailService.findBySubj(Obj);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/findById")
	public String findById(@RequestBody Email Obj) {
		return mailService.findById(Obj);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(value = "/deleteById")
	public String deleteById(@RequestBody Email Obj) {
		return mailService.deleteById(Obj);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/getAll")
	public ArrayList<Email> getAll() {
		return mailService.getAll();
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(value = "/deleteAll")
	public String deleteAll() {
		return mailService.deleteAll();
	}
}
