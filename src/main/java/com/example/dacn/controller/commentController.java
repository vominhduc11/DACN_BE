package com.example.dacn.controller;

import com.example.dacn.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class commentController {
    @Autowired
    private commentService commentService;

    @GetMapping(value = "/getAllComment/{idUser}/{idProduct}")
    public List<Map<String, Object>> getAllComment(@PathVariable int idUser, @PathVariable int idProduct) {
        List<Map<String, Object>> result = commentService.getAllComment(idUser, idProduct);

        return result;
    }

    @PostMapping(value = "/addComment")
    public void addComment(@RequestBody Map<String, Object> data) {
        int idUser = (int) data.get("idUser");
        int idProduct = (int) data.get("idProduct");
        Number star = (Number) data.get("star");
        String time = (String) data.get("time");
        String content = (String) data.get("content");

        commentService.addComment(idUser,idProduct,star,time,content);
    }
}
