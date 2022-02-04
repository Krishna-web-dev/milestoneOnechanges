package com.firstProject.Test;

import com.firstProject.User;
import com.firstProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void initUseCase() {
        List<User> customers = Arrays.asList(
                new User("Vivek0099", "Vivek", "Patel", "vivek.patel@paytm.com", "8484339901", "Pune", "Gwalior")
        );
        userRepository.saveAll(customers);
    }



    @Test
    void findAll_success() {
        List<User> allCustomer = userRepository.findAll();
        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
    }

}