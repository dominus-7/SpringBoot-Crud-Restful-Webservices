package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.RessourceNotFoundException;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	//get All Users
	
	@GetMapping
	List<User> getAllUsers(){
		
		return this.userRepository.findAll();
		
	}
	
	//get User By Id
	
	@GetMapping("/{id}")
	
	public User getUserById(@PathVariable(value = "id") long userId) {
		return userRepository.findById(userId)
				.orElseThrow(()->new RessourceNotFoundException("Ressource not found with id:"+userId));
	}
	//create User
	
	@PostMapping
	
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}
	
	//update User
	@PutMapping("/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable(value = "id") long userId) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(()->new RessourceNotFoundException("Ressource not found with id:"+userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		
		return this.userRepository.save(existingUser);
	}
	
	//delete User By Id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") long userId){
		User existingUser = userRepository.findById(userId)
				.orElseThrow(()->new RessourceNotFoundException("Ressource not found with id:"+userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}

}
