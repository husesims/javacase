package com.worldline.casestudy.prime;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class PrimeService  {

    private Boolean isPrime(int number, ArrayList<Integer> primes) {
        for (int p:primes) {
            if(number % p == 0) {
                return false;
            }
        }
        return  true;
    }
    /**
     * uses a primes array to store already found ones, and use this array to check current number whether is prime.
     * /
     */
    public List<Integer> getPrimes(int n) throws  IllegalArgumentException {
        if(n<0) throw new IllegalArgumentException("number can not be less than zero");
        if(n<=1) return new ArrayList<>(); // return empty list

        ArrayList<Integer> primes = new ArrayList<>();
        primes.add(Integer.valueOf(2));
        if(n==2) return  primes;

        for (int i = 2; i <=n ; i++) {
            if(isPrime(i, primes)) {
                primes.add(i);
            }
        }
        return  primes;
    }
}
