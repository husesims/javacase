package com.worldline.casestudy.prime;

import com.worldline.casestudy.fibonacci.FibonacciService;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class PrimeServiceTest {

    private  PrimeService primeService;

    @BeforeEach
    void initTest() {
        primeService = new PrimeService();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void getPrimesEdgeCase() {
        List<Integer> expected= Arrays.asList(2);
        assertThat(primeService.getPrimes(2)).isEqualTo(expected);
    }

    @Test
    void getPrimes() {
        List<Integer> expected= Arrays.asList(2,3,5,7);
        assertThat(primeService.getPrimes(10)).isEqualTo(expected);
    }
}