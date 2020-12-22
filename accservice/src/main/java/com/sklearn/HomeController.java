package com.sklearn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class HomeController {
	
	@GetMapping("/healthcheck")
	public ResponseEntity<String> statuscheck() {
		return new ResponseEntity<String>("Working", HttpStatus.OK);
	}
}

