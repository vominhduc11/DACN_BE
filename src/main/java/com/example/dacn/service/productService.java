package com.example.dacn.service;

import com.example.dacn.entity.product;
import com.example.dacn.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    public product getProduct(int id) {
        Optional<product> productOptional = productRepository.findById(id);
        product productObj = productOptional.get();
        return productObj;
    }
}
