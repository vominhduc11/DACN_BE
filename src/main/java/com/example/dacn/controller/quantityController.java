package com.example.dacn.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class quantityController {

    @PostMapping(value = "/addQuantity")
    public void addQuantity(@RequestBody Map<String, Object> data) {
        String age = (String) data.get("age");
        int price = (int) data.get("price");
        int idPackage = (int) data.get("idPackage");

    }
}
