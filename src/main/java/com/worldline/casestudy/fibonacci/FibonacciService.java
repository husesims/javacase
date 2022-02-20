package com.worldline.casestudy.fibonacci;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FibonacciService {
    /**
     * Complexity O(n), memory usage O(1)
     * simple adds previous 2 fibonacci numbers to find the next one
     * /
     */
    public BigInteger getNumberAt(int index) throws IllegalArgumentException {
        if (index<0) throw new IllegalArgumentException("index can not be less than zero");
        if(index ==0 ) return   BigInteger.valueOf(0); //for edge case

        BigInteger n=BigInteger.valueOf(1), n_minus_1=BigInteger.valueOf(1), n_minus_2=BigInteger.valueOf(0);
        for (int i = 2; i <= index; i++) {
            n=n_minus_1.add(n_minus_2);
            n_minus_2 = n_minus_1;
            n_minus_1 =n;
        }
        return  n;
    }
}
