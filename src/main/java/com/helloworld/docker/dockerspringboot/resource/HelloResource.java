package com.helloworld.docker.dockerspringboot.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/docker/hello")
public class HelloResource {
	
	@Autowired
	public UsersController usersController;
	
	@GetMapping
	public String hello() {
		Users testUser = usersController.getUserByName("Charles Weems");
		System.out.println("This user's name is: " + testUser.getName());
		return "This user is: " + testUser.getName();
	}

}
