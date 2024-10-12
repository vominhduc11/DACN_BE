package com.example.dacn.controller;

import com.example.dacn.entity.product;
import com.example.dacn.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class productController {

    @Autowired
    private productService productService;

    @CrossOrigin(origins = {"http://localhost:8081","http://127.0.0.1:5501"})
    @GetMapping(value = "/getProduct/{id}")
    public product getProduct(@PathVariable("id") int id) {
        product product = productService.getProduct(id);
        return product;
    }
;}
