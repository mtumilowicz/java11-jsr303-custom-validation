package com.example.validation.app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by mtumilowicz on 2019-03-05.
 */
@RestController
public class HelloController {
    
    @PostMapping("/")
    public ResponseEntity<User> generic(@RequestBody @Valid User user) {
        return ResponseEntity.ok(user);
    }
}
