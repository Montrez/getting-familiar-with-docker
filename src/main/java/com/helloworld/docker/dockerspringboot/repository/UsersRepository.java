package com.helloworld.docker.dockerspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.helloworld.docker.dockerspringboot.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	@Query("SELECT u FROM Users u WHERE u.name = :name")
	public Users findByName(@Param("name") String name);
}
