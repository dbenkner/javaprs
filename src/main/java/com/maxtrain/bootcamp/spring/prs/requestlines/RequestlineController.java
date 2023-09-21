package com.maxtrain.bootcamp.spring.prs.requestlines;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.bootcamp.spring.prs.product.Product;
import com.maxtrain.bootcamp.spring.prs.product.ProductRepository;
import com.maxtrain.bootcamp.spring.prs.request.Request;
import com.maxtrain.bootcamp.spring.prs.request.RequestRepository;

@CrossOrigin
@RestController
@RequestMapping("api/requestlines")
public class RequestlineController {
	@Autowired
	RequestlineRepository requestlineRepo;
	@Autowired
	RequestRepository requestRepo;
	@Autowired
	ProductRepository productRepo;
	
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
	@PostMapping
	public ResponseEntity<Requestline> addRequestline(@RequestBody Requestline requestline)
	{
		if (requestline.getId() != 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (requestline.getQuantity() <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		requestlineRepo.save(requestline);
		recalculateOrder(requestline.getRequest().getId());
		return new ResponseEntity<Requestline> (requestline, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<Requestline> updateRequestline(@RequestBody Requestline requestline, @PathVariable int id)
	{
		if (requestline.getId() != id) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (requestline.getQuantity() <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		requestlineRepo.save(requestline);
		recalculateOrder(requestline.getRequest().getId());
		return new ResponseEntity<Requestline> (requestline, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity deleteRequestline(@PathVariable int id)
	{
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Requestline> rl = requestlineRepo.findById(id);
		int reqId = rl.get().getRequest().getId();
		requestlineRepo.deleteById(id);
		recalculateOrder(reqId);
		return new ResponseEntity (HttpStatus.NO_CONTENT);
	}

	private void recalculateOrder(int id) {
		double result = requestlineRepo.calculateTotalCostByRequestId(id);
		Optional<Request>req = requestRepo.findById(id);
		req.get().setTotal(result);
		requestRepo.save(req.get());
	}
	
}
