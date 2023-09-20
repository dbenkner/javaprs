package com.maxtrain.bootcamp.spring.prs.vendor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/vendors")
public class VendorController {
	@Autowired
	VendorRepository vendorRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Vendor>> getAllVendors(){
		Iterable<Vendor> vendors = vendorRepo.findAll();
		return new ResponseEntity<Iterable<Vendor>> (vendors, HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		Optional<Vendor> vendor = vendorRepo.findById(id);
		if (vendor.isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Vendor> (vendor.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<Vendor> addNewVendor(@RequestBody Vendor vendor){
		if (vendor.getId() != 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		vendorRepo.save(vendor);
		return new ResponseEntity<Vendor> (vendor, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<Vendor> updateVendor(@RequestBody Vendor vendor, @PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		if (vendor.getId() != id) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		vendorRepo.save(vendor);
		return new ResponseEntity<Vendor> (vendor, HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity deleteVendor(@PathVariable int id){
		if (id <= 0) return new ResponseEntity (HttpStatus.BAD_REQUEST);
		vendorRepo.deleteById(id);
		return new ResponseEntity (HttpStatus.NO_CONTENT);
	}
}
