package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

public class HistoryController {
	\@CrossOrigin(origins = "*")
	@PostMapping(value = "/importLeadHistory")
	public ResponseEntity<?> importLeadHistory(@RequestPart(value = "file") MultipartFile file,@RequestParam String userid) {

		System.out.println("inside controller");
		JsonObject result = activityService.importLeadHistory(file);
		
    	responseEntity = new ResponseEntity<>(result.toString(), HttpStatus.OK);
    	
		return responseEntity;
	}

}
