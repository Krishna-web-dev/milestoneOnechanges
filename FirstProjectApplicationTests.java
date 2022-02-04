package com.firstProject;

import com.firstProject.repository.UserRepository;
import com.firstProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class FirstProjectApplicationTests {
	// if autowired then repo also gets autowired

	private UserService userService;


	// getting mock repository
	@Mock
	private UserRepository userRepository;


	@BeforeEach
	void setup()
	{
		this.userService = new UserService(userRepository);

	}
	//test writting
	void test_function_getAllPerson()
	{

	}


}
