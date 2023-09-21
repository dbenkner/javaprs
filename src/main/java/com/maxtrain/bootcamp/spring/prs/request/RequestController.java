package com.maxtrain.bootcamp.spring.prs.request;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.bootcamp.spring.prs.requestlines.Requestline;
import com.maxtrain.bootcamp.spring.prs.requestlines.RequestlineRepository;


@CrossOrigin
@RestController
@RequestMapping("api/requests")
public class RequestController {
	@Autowired
	RequestRepository requestRepo;
	@Autowired
	RequestlineRepository rlRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Request>> getAllRequests(){
		Iterable<Request> requests = requestRepo.findAll();
		return new ResponseEntity<Iterable<Request>> (requests, HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Request> getRequestsById(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Request> request = requestRepo.findById(id);
		if (request.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		return new ResponseEntity<Request> (request.get(), HttpStatus.OK);
	}
	@GetMapping("/reviews/{userId}")
	public ResponseEntity<Iterable<Request>> getReviews (@PathVariable int userId){
		if (userId <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Iterable<Request> requests = requestRepo.findAllByUserIdNot(userId);
		if (requests == null) return new ResponseEntity (HttpStatus.NOT_FOUND);
		return new ResponseEntity<Iterable<Request>> (requests, HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Request> addNewRequest(@RequestBody Request request){
		if (request.getId() != 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		requestRepo.save(request);
		return new ResponseEntity<Request> (HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<Request> updateRequest(@RequestBody Request request, @PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (request.getId() != id) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		requestRepo.save(request);
		return new ResponseEntity<Request> (request, HttpStatus.OK);
	}
	@PutMapping("review/{id}")
	public ResponseEntity<Request> reviewRequest(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Request> request = requestRepo.findById(id);
		if (request.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		if (request.get().getTotal() <= 50) {
			request.get().setStatus("APPROVED");
		}
		else {
			request.get().setStatus("REVIEW");
		}
		requestRepo.save(request.get());
		return new ResponseEntity<Request> (request.get(), HttpStatus.OK);
	}
	@PutMapping("approve/{id}")
	public ResponseEntity<Request> approveRequest(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Request> request = requestRepo.findById(id);
		if (request.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		request.get().setStatus("APPROVED");
		requestRepo.save(request.get());
		return new ResponseEntity<Request> (request.get(), HttpStatus.OK);
	}
	@PutMapping("reject/{id}")
	public ResponseEntity<Request> rejectRequest(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Request> request = requestRepo.findById(id);
		if (request.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		request.get().setStatus("REJECTED");
		requestRepo.save(request.get());
		return new ResponseEntity<Request> (request.get(), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequest(@PathVariable int id) {
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		requestRepo.deleteById(id);
		return new ResponseEntity (HttpStatus.OK);
	}
}
