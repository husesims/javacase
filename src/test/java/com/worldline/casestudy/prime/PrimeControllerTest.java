package com.worldline.casestudy.prime;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PrimeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private PrimeService primeService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void prime() throws Exception {

        when(primeService.getPrimes(10))
                .thenReturn(List.of(2,3,5,7));

        mockMvc.perform(
                        get("/prime/10")
                ).andDo(print())
                .andExpect(status().isOk()

                ).andExpect( content().string("2,3,5,7"));
    }

    @Test
    void prime_illegal_argument() throws Exception {
        when(primeService.getPrimes(-1))
                .thenThrow(new IllegalArgumentException("illegal exception"));
        mockMvc.perform(
                        get("/prime/-1")
                ).andDo(print())
                .andExpect(status().isBadRequest()
                );
    }
}