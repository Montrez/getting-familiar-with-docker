package com.helloworld.docker.dockerspringboot.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// New add imports
import org.springframework.beans.factory.annotation.Autowired;
import com.helloworld.docker.dockerspringboot.controller.UsersController;
import com.helloworld.docker.dockerspringboot.model.Users;


@RestController
@RequestMapping("/rest/docker/hello")
public class HelloResource {
	@Autowired
	public UsersController usersController;


	@GetMapping
	public String hello() {
		Users testUser = usersController.getUserByName("Charles Weems");
		System.out.println("This user's name is: " + testUser.getName());
		System.out.println("We are here.");
		return "Hello, good afternoon " + testUser.getName();
	}

}
