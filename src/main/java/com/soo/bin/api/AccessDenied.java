package com.soo.bin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessDenied {

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "No access for you!";
    }
}
