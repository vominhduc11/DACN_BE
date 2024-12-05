package com.example.dacn.controller;

import com.example.dacn.entity.user;
import com.example.dacn.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class userController {
    @Autowired
    private userService userService;

    @GetMapping(value = "/getUser/{idUser}")
    public user getUser(@PathVariable int idUser){
       user user = userService.getUser(idUser);
       return user;
    }

    @PostMapping(value = "/addUser")
    public String addUser( @RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("fileImage") MultipartFile multipartFile) {
        String result = userService.addUser(name,email,password,multipartFile);
        return  result;
    }

    @PostMapping(value = "/authenAccount")
    public Integer authenAccount(@RequestBody Map<String,Object> data) {
        String Email = (String) data.get("email");
        String Password = (String) data.get("password");
        Integer result = userService.authenAccount(Email,Password);
         
        return result;
    }
}
