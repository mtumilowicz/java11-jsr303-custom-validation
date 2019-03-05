package com.example.validation.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mtumilowicz on 2019-03-05.
 */
@RestController
public class HelloController {
    
    @GetMapping("/")
    public String generic() {
        return "Greetings!";
    }
}
