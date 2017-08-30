package com.springjpa.auth.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springjpa.auth.entity.User;
import com.springjpa.auth.repository.RoleRepository;
import com.springjpa.auth.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceImplTest {
	
	@Configuration
	static class UserServiceTextContextConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}
		@Bean
		public UserRepository userRepository() {
			return Mockito.mock(UserRepository.class);
		}
		@Bean
		public RoleRepository roleRepository() {
			return Mockito.mock(RoleRepository.class);
		}
	}
	
	@Autowired
	private UserRepository userRepository;
	 
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByName() {
		String userName = "Julien";
		User user = new User();
		Mockito.when(userRepository.findByUsername(userName)).thenReturn(user);
		
	}

}
