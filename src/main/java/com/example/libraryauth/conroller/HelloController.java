
package com.example.libraryauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/user/hello")
    public String userHello() {
        return "Привет, читатель!";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Привет, библиотекарь!";
    }
}
