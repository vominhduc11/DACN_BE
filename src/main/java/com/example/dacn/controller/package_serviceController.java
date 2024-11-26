package com.example.dacn.controller;

import com.example.dacn.service.package_serviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class package_serviceController {
    @Autowired
    private package_serviceService package_serviceService;

    @PostMapping("/addPackage")
    public void addPackage(@RequestBody List<Map<String, Object>> data) {
       package_serviceService.addPackage(data);
    }
}
