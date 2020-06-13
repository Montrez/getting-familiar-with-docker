package com.helloworld.docker.dockerspringboot.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.helloworld.docker.dockerspringboot.exception.ResourceNotFoundException;
import com.helloworld.docker.dockerspringboot.model.Users;
import com.helloworld.docker.dockerspringboot.repository.UsersRepository;

@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}
	
	@PostMapping("/users")
	public Users createUser(@Valid @RequestBody Users user) {
		return usersRepository.save(user);
	}
	
	@GetMapping("/users/{user_id}")
	public Users getUserByUser_id(@PathVariable(value = "user_id") int user_id) {
		return usersRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", user_id));
	}
	
	@GetMapping("/users/{name}")
	public Users getUserByName(@PathVariable(value = "name") String name) {
		return usersRepository.findByName(name);
	}
	
	@PutMapping("/users/{user_id}")
	public Users updateUser(@PathVariable(value = "user_id") int user_id, @Valid @RequestBody Users userDetails) {
		Users user = usersRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", user_id));
		
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		user.setName(userDetails.getName());
		user.setEnabled(userDetails.isEnabled());
		
		Users updatedUser = usersRepository.save(user);
		return updatedUser;
	}
	
	@DeleteMapping("/users/{user_id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "user_id") int user_id) {
		Users user = usersRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "user_id", user_id));
		
		usersRepository.delete(user);
		
		return ResponseEntity.ok().build();
	}
	
}
