package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping(value = "admin")
    public String getTestData() {
            Test test = new Test();
            System.out.println(test.ttClass.test);
            return "test admin data";
    }

    public class Test {
        public TtClass ttClass;
    }
    public class TtClass {
        public String test;
    }
}
