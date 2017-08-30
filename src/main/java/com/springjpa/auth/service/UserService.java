package com.springjpa.auth.service;

import com.springjpa.auth.entity.User;

public interface UserService {
	
	public void save (User newUser);
	
	public User findUserByName(String username);
	
	public User findUserByMail(String email);
	
	public void createPasswordResetTokenForUser(User user, String token);

}
