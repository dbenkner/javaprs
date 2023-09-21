package com.maxtrain.bootcamp.spring.prs.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/products")
@RestController
public class ProductController {
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> getAllProducts(){
		Iterable<Product> products = productRepo.findAll();
		return new ResponseEntity<Iterable<Product>> (products, HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductByID(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Product> product = productRepo.findById(id);
		if (product.isEmpty()) return new ResponseEntity (HttpStatus.NOT_FOUND);
		return new ResponseEntity<Product> (product.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product){
		if (product.getId() != 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (product.getPrice() <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		productRepo.save(product);
		return new ResponseEntity<Product> (product, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (product.getId() != id) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		productRepo.save(product);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
		}
	@DeleteMapping("{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id)
	{
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		productRepo.deleteById(id);
		return new ResponseEntity (HttpStatus.BAD_REQUEST);
	}
}
