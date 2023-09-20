package com.maxtrain.bootcamp.spring.prs.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	public UserRepository userRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getAllUsers(){
		Iterable<User> users = userRepo.findAll();
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		if (id <= 0) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		Optional<User> user = userRepo.findById(id);
		if (user.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	@GetMapping("/{username}/{password}")
	public ResponseEntity<User> loginUser(@PathVariable String username, @PathVariable String password)
	{
		Optional<User> user = userRepo.findUserByUsernameAndPassword(username, password);
		if (user.isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<User> addNewUser(@RequestBody User user){
		if (user.getId() != 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		userRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id)
	{
		if (id <= 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		userRepo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteUser (@PathVariable int id) {
		if (id <= 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		userRepo.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
