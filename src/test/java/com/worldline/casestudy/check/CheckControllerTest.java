package com.worldline.casestudy.check;

import com.worldline.casestudy.fibonacci.FibonacciService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CheckControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private PingService pingService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void check() throws Exception {
        when(pingService.isValidURL("http://wwww.google.com"))
                .thenReturn(true);
        when(pingService.isOK(eq("http://wwww.google.com"), anyInt()))
                .thenReturn(true);
        mockMvc.perform(
                        get("/check?url=http://wwww.google.com")
                ).andDo(print())
                .andExpect(status().isOk()
                ).andExpect(content().string("true"));
    }

    @Test
    void check_not_ok() throws Exception {
        when(pingService.isValidURL("http://wwww.google.com"))
                .thenReturn(true);
        when(pingService.isOK(eq("http://wwww.google.com"), anyInt()))
                .thenReturn(false);
        mockMvc.perform(
                        get("/check?url=http://wwww.google.com")
                ).andDo(print())
                .andExpect(status().isOk()
                ).andExpect(content().string("false"));
    }

    @Test
    void check_illegal_argument() throws Exception {
        when(pingService.isValidURL("htt://wwww.google.com"))
                .thenReturn(false);;
        mockMvc.perform(
                        get("/check?url=htt://wwww.google.com")
                ).andDo(print())
                .andExpect(status().isBadRequest()
                );
    }
}