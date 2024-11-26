package com.example.dacn.controller;

import com.example.dacn.entity.country;
import com.example.dacn.service.countryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class countryController {
    @Autowired
    private countryService countryService;

    // Lấy tất cả sản phẩm yêu thích
    @GetMapping(value = "/getAllCountry")
    public List<country> getAllCountry() {
        List<country> result = countryService.getAllCountry();

        return result;
    }
}
