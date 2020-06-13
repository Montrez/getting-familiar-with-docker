package com.helloworld.docker.dockerspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.helloworld.docker.dockerspringboot.model.Users;

@Controller
public class UserCRUDController {

	@Autowired
	private UsersController usersController;
	
	@RequestMapping("/user-list")
	public String viewUserCRUDPage(Model model) {
		List<Users> userList = usersController.getAllUsers();
		
		model.addAttribute("userList", userList);
		return "user-list";
	}
	
	@RequestMapping("/new-user")
	public String viewNewUserPage(Model model) {
		List<Users> userList = usersController.getAllUsers();
		Users newUser = new Users();
		
		model.addAttribute("userList", userList);
		model.addAttribute("newUser", newUser);
		
		return "new-user";
	}
	
	@RequestMapping(value = "/save-user", method = RequestMethod.POST)
	public String saveUser(Model model, @ModelAttribute("user") Users user) {
		List<Users> userList = usersController.getAllUsers();
		
		user.setEnabled(true);
		usersController.createUser(user);
		
		Users savedNewUser = usersController.getUserByUser_id(user.getUser_id());
		
		model.addAttribute("userList", userList);
		model.addAttribute("savedNewUser", savedNewUser);
		
		return "user-list";
	}
	
	@RequestMapping("/edit/{user_id}")
	public String showEditUserPage(Model model, @PathVariable(name = "user_id") int user_id) {
		Users user = usersController.getUserByUser_id(user_id);
		List<Users> userList = usersController.getAllUsers();
		
		model.addAttribute("userList", userList);
		model.addAttribute("user", user);
		
		return "edit-user";
	}
	
	@RequestMapping("/editsave/{user_id}")
	public String saveEditedUser(Model model, @ModelAttribute("user") Users user, @PathVariable(name = "user_id") int user_id) {
		Users editedUser = usersController.getUserByUser_id(user_id);
		
		editedUser.setUsername(user.getUsername());
		editedUser.setPassword(user.getPassword());
		editedUser.setName(user.getName());
		usersController.updateUser(user_id, editedUser);
		
		List<Users> userList = usersController.getAllUsers();
		Users savedEditUser = usersController.getUserByUser_id(user_id);
		
		model.addAttribute("userList", userList);
		model.addAttribute("savedEditUser", savedEditUser);
		
		return "user-list";
	}
	
	@RequestMapping("/delete/{user_id}")
	public String deleteUser(@PathVariable(name = "user_id") int user_id) {
		usersController.deleteUser(user_id);
		
		return "redirect:/user-list";
	}
}
