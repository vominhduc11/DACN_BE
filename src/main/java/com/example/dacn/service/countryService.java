package com.example.dacn.service;

import com.example.dacn.entity.comment;
import com.example.dacn.entity.country;
import com.example.dacn.repository.countryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class countryService {
    @Autowired
    private countryRepository countryRepository;

    public List<country> getAllCountry() {
        List<country> countries = countryRepository.findAll();
        return countries;
    }
}
