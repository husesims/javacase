package com.worldline.casestudy.prime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
public class PrimeController {
    private final PrimeService primeService;
    //Logger logger = LoggerFactory.getLogger(PrimeController.class);
    public PrimeController(PrimeService primeService) {
        this.primeService = primeService;
    } //dependency injection

    @GetMapping(path = "prime/{number}")
    public String fibonacci(@PathVariable int number) {
        try {
            var list= primeService.getPrimes(number);
            //in c# it is simple String.Join, unfortunately in Java the best method is a little bit complex. a naive solution is iterating in list loop and append to StringBuilder.
            return list.stream().map(Object::toString)
                    .collect(Collectors.joining(","));
        } catch (IllegalArgumentException ex) {
            //logger.info(" Illegal Parameter ({}) entered ", number);
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "illegal number");
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred");
        }
    }
}

