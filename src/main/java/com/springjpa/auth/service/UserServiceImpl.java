package com.springjpa.auth.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springjpa.auth.dto.PasswordResetTokenDto;
import com.springjpa.auth.entity.PasswordResetToken;
import com.springjpa.auth.entity.User;
import com.springjpa.auth.repository.PasswordResetTokenRepository;
import com.springjpa.auth.repository.RoleRepository;
import com.springjpa.auth.repository.UserRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 private UserRepository userRepository;
	 
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User findUserByMail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetTokenDto newTokenDto = new PasswordResetTokenDto(token, user);
		PasswordResetToken newToken =new PasswordResetToken();
		newToken.setUser(newTokenDto.getUser());
		newToken.setToken(newTokenDto.getToken());
		newToken.setExpirationDate(newTokenDto.getExpirationDate());
		passwordResetTokenRepository.save(newToken);

	}

}
