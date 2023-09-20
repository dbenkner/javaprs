package com.maxtrain.bootcamp.spring.prs.requestlines;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/requestlines")
public class RequestlineController {
	@Autowired
	RequestlineRepository requestlineRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Requestline>> getAllRequestlines(){
		Iterable<Requestline> requestlines = requestlineRepo.findAll();
		return new ResponseEntity<Iterable<Requestline>> (requestlines, HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Requestline> getRequestById(@PathVariable int id)
	{
		if (id <=0 ) return new ResponseEntity (HttpStatus.BAD_REQUEST);
	
		Optional<Requestline> requestline = requestlineRepo.findById(id);
		if (requestline.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		return new ResponseEntity<Requestline> (requestline.get(), HttpStatus.OK);
	}
	
}
