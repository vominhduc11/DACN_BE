package com.example.dacn.service;

import com.example.dacn.entity.city;
import com.example.dacn.repository.cityRepository;
import com.example.dacn.repository.countryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class cityService {

    @Autowired
    private cityRepository cityRepository;

    @Autowired
    private countryRepository countryRepository;
    public int getAmountCity() {
        long Amount = cityRepository.count();
        return (int) Amount;
    }

    public city getCity(int randomNumber) {
        Optional<city> cityOptional = cityRepository.findById(randomNumber);
        city cityObj = cityOptional.get();
        return cityObj;
    }

    public Set<city> get6TopCity() {
        Set<city> cities = cityRepository.findTop6ByOrderByIdAsc();

        return cities;
    }

    public city getCity(String name) {
        city city = cityRepository.findByName(name);

        return city;
    }

    public List<Map<String, Object>> getListCityAccordingString(String debounce) {
        List<city> citys = cityRepository.findTop5ByNameContainingIgnoreCase(debounce);

        List<Map<String, Object>> cityDTOs = new ArrayList<>();


        for (city city : citys) {
            Map<String, Object> productDTO = new HashMap<>() {{
                put("id", city.getId());
                put("name", city.getName());
                put("country", city.getCountry().getName());
            }};

            cityDTOs.add(productDTO);
        }

        return cityDTOs;
    }

    public List<city> getAllCity(int id) {
        Optional<city> cityOptional = cityRepository.findById(id);
        city city = cityOptional.get();

        int id_product = city.getCountry().getId();
        List<city> cities = cityRepository.findAllByCountry_Id(id_product);

        return cities;
    }

    public Set<city> get10City() {
        Set<city> cities = cityRepository.findTop10ByOrderByIdAsc();

        return  cities;
    }
}
