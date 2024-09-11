package com.bootcamp.eventorg.auth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
public class HomeController {
    @GetMapping("/")
    public String guest() {
        return "Hello, Guest!";
    }
}
