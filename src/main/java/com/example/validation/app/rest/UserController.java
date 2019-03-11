package com.example.validation.app.rest;

import com.example.validation.app.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mtumilowicz on 2019-03-05.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping
    public ResponseEntity<Boolean> health() {
        return ResponseEntity.ok(true);
    }
    
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody @Valid User user) {
        return ResponseEntity.ok(user);
    }
}
