package com.example.dacn.controller;

import com.example.dacn.service.favoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class favoriteController {
    @Autowired
    private favoriteService favoriteService;

    // Lấy tất cả sản phẩm yêu thích
    @GetMapping(value = "/getAllProductFavorite/{idUser}")
    public List<Map<String, Object>> getAllProductFavorite(@PathVariable int idUser) {
        List<Map<String, Object>> result = favoriteService.getAllProductFavorite(idUser);

        return result;
    }

    // Thêm sản phẩm yêu thích
    @PostMapping(value = "/addProductFavorite")
    public void addProductFavorite(@RequestBody Map<String, Object> data) {
        int idProduct = (int) data.get("idProduct");
        int idUser = (int) data.get("idUser");
        favoriteService.addProductFavorite(idProduct, idUser);
    }

    // Xóa sản phẩm yêu thích
    @DeleteMapping(value = "/deleteProductFavorite/{idUser}/{idProduct}")
    public void deleteProductFavorite(@PathVariable int idUser, @PathVariable int idProduct) {
        favoriteService.deleteProductFavorite(idProduct, idUser);
    }

}
