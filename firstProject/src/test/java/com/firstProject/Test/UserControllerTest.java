package com.firstProject.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstProject.controller.UserController;
import com.firstProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.firstProject.User;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
@Autowired
    private MockMvc mockMvc;
@Autowired
    private ObjectMapper objectMapper;

@MockBean
    private UserService userService;

    User krishna = new User("kris@123","krishna","verma","649846415","krishna@reddit","gwlaior","bhopal");
    User bharat = new User("bharat@123","bharat","verma","6846166","bharat@gmail","gwlaior","bhopal");

    @Test
    void getAllUser() throws Exception{
        Mockito.when(userService.getAllUsers()).thenReturn(Arrays.asList(krishna,bharat));

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user")
        )
                .andExpect(status().isOk())
                .andDo(print());


    }
    @Test
    void getUser() throws Exception{
        Mockito.when(userService.getUser(krishna.getUsername())).thenReturn(krishna);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/kris@123")
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addUser() throws Exception{
            User shweta = User.builder()
                    .username("Sweta1999")
                    .firstname("Sweta")
                    .lastname("Singh")
                    .email("sweta@gmail.com")
                    .mobile("7879030334")
                    .address1("SBI Chawk")
                    .address2("Madan Mahal").build();

        User bharat = User.builder()
                .username("bharat1998")
                .firstname("bharat")
                .lastname("Singh")
                .email("bharat@gmail.com")
                .mobile("54864651")
                .address1("bhopal")
                .address2("gwalior").build();
        System.out.println(shweta);
        System.out.println(bharat);
        Mockito.when(userService.addUser(shweta)).thenReturn(bharat);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(shweta));
        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void updateUser() throws Exception {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<?> responseEntity = new ResponseEntity<>(
                "Success",
                header,
                HttpStatus.OK
        );
        User sonu = User.builder()
                .username("sonu1998")
                .firstname("sonu")
                .lastname("Singh")
                .email("sonusingh@gmail.com")
                .mobile("648156")
                .address1("sayna")
                .address2("mehgaon").build();
        userService.addUser(sonu);
        Mockito.when(userService.updateUser(sonu,sonu.getUsername())).thenReturn(responseEntity);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/user/sonu1998")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(sonu));

    mockMvc.perform(mockRequest)
            .andDo(print())
            .andExpect(status().isOk());

    }

    @Test
    void deleteUser() throws  Exception{
        userService.addUser(bharat);
        Mockito.when(userService.delete(bharat.getUsername(),bharat)).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(("/user/bharat1998"))
        )
                .andDo(print());

    }
}
