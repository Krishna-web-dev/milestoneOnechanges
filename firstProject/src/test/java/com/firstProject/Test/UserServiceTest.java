package com.firstProject.Test;

import com.firstProject.User;
import com.firstProject.repository.UserRepository;
import com.firstProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    private UserService userService;


    public User user = new User("Vivek0099", "Vivek", "Patel", "vivek.patel@paytm.com","8484339901","Pune", "Gwalior");

    @BeforeEach
    void setUp() {

        this.userService = new UserService(this.userRepository);
    }

    @BeforeEach
    void initUseCase() {
        List<User> customers = Arrays.asList(
                new User("Vivek0099", "Vivek", "Patel", "vivek.patel@paytm.com","8484339901","Pune", "Gwalior")
        );

        userRepository.saveAll(customers);
    }

    @Test
    void getAllUser() {
        List<User> users = userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void getSpecificUser() {

        User user = new User("harshit0099", "harshit", "jain", "6545164531","harsiht@gmail.com","delhi", "Gwalior");

        String username = "harshit009";

        Mockito.when(userService.getUser(username)).thenReturn(user);
        verify(userRepository).findById(username);
    }

    @Test
    void addNewUser() {
        User krishna = new User("kris@123","krishna","verma","649846415","krishna@reddit","gwlaior","bhopal");
        User bharat = new User("bharat@123","bharat","verma","6846166","bharat@gmail","gwlaior","bhopal",1);

        // Providing Knowledge
        when(userRepository.save(krishna)).thenReturn(bharat);

        User savedUser = userRepository.save(user);
        assertThat(bharat.getId()).isEqualTo(1);
    }

}
