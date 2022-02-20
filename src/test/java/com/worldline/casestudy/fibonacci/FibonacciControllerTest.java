package com.worldline.casestudy.fibonacci;
import java.math.BigInteger;
import java.util.Arrays;
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


import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private FibonacciService fibonacciService;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void fibonacci() throws Exception {
        when(fibonacciService.getNumberAt(50))
                .thenReturn(BigInteger.valueOf(12586269025L));
        mockMvc.perform(
                        get("/fibonacci/50")
                ).andDo(print())
                .andExpect(status().isOk()

                ).andExpect( content().string("12586269025"));
    }

    @Test
    void fibonacci_illegal_argument() throws Exception {
        when(fibonacciService.getNumberAt(-1))
                .thenThrow(new IllegalArgumentException("illegal exception"));
        mockMvc.perform(
                        get("/fibonacci/-1")
                ).andDo(print())
                .andExpect(status().isBadRequest()
                );
    }

}

