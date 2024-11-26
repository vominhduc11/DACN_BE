package com.example.dacn.controller;

import com.example.dacn.entity.city;
import com.example.dacn.service.cityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class cityController {
    @Autowired
    private cityService cityService;

    // Lấy 1 thành phố ngẫu nhiên
    @GetMapping(value = "/getCity")
    public city getCity() {
        int amount_city = cityService.getAmountCity();

        Random random = new Random();
        int randomNumber = random.nextInt(amount_city + 1);

        city city = cityService.getCity(randomNumber);

        return  city;
    }

    // Lấy thành phố theo id thành phố
    @GetMapping(value = "/getCity/{id}")
    public city getCity(@PathVariable("id") int id) {
        city city = cityService.getCity(id);

        return city;
    }

    // Lấy danh sách thành phố 6 cái
    @GetMapping(value = "/getListCity")
    public Set<city> getListCity() {
        Set<city> cities = cityService.get6TopCity();

        return  cities;
    }

    // Lấy thành phố theo tên
    @GetMapping(value = "/getCityByName/{name}")
    public city getCity(@PathVariable("name") String name) {
        System.out.println(name);
        city city = cityService.getCity(name);

        return city;
    }

    // Lấy thành phố theo chuỗi
    @GetMapping(value = "/getListCityAccordingString/{debounce}")
    public List<Map<String, Object>> getListCityAccordingString(@PathVariable("debounce") String debounce) {
        List<Map<String, Object>> objects = cityService.getListCityAccordingString(debounce);

        return objects;
    }

    // Lấy tất cả thành phố từ 1 id thành phố bất kì
    @GetMapping(value = "/getAllCity/{id}")
    public List<city> getAllCity(@PathVariable("id") int id) {
        List<city> objects = cityService.getAllCity(id);

        return objects;
    }

    // Lấy tất cả thành phố từ 1 id thành phố bất kì
    @GetMapping(value = "/get10City")
    public Set<city> get10City() {
        Set<city> result = cityService.get10City();

        return result;
    }
}
