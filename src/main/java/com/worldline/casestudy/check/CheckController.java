package com.worldline.casestudy.check;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CheckController {

    private final PingService pingService;

    public CheckController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping(path = "check")
    public boolean check(@RequestParam("url") String url)  {
        if(!pingService.isValidURL(url)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "invalid url");
        }

        return pingService.isOK(url, 2000);
    }
}
