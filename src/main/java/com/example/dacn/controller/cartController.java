package com.example.dacn.controller;

import com.example.dacn.service.cartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class cartController {
    @Autowired
    private cartService cartService;

    // Lấy tất cả các sản phẩm giỏ hàng
    @GetMapping(value = "/getAllProductCart/{idUser}")
    public List<Map<String, Object>> getAllProductCart(@PathVariable int idUser) {
        List<Map<String, Object>> carts = cartService.getAllProductCart(idUser);

        return carts;
    }

    // Kiểm tra có sản phẩm hay không
    @GetMapping(value = "/checkIfExists/{idPackage}/{idUser}")
    public boolean checkIfExists(@PathVariable int idPackage, @PathVariable int idUser) {
        boolean result = cartService.checkIfExists(idPackage, idUser);
        return result;
    }

    // Sửa lại sản phẩm
    @PutMapping(value = "/partialUpdateProductCart")
    public void partialUpdateProductCart(@RequestBody Map<String, Object> data) {
        int idPackage = (int) data.get("idPackage");
        int idUser = (int) data.get("idUser");
        int idProduct = (int) data.get("idProduct");
        String quantitys = (String) data.get("quantitys");
        cartService.partialUpdateProductCart(idPackage,idProduct ,idUser, quantitys);
    }

    // Thêm sản phẩm giỏ hàng
    @PostMapping(value = "/addProductCart")
    public void addProductCart(@RequestBody Map<String, Object> data) {
        int idProduct = (int) data.get("idProduct");
        int idPackage = (int) data.get("idPackage");
        int idUser = (int) data.get("idUser");
        String quantitys = (String) data.get("quantitys");
        cartService.addProductCart(idProduct, idPackage, idUser, quantitys);
    }

    // Xóa sản phẩm giỏ hàng
    @DeleteMapping(value = "/deleteProductCart/{idPackage}/{idUser}")
    public void deleteProductCart(@PathVariable int idPackage, @PathVariable int idUser) {
         cartService.deleteProductCart(idPackage,idUser);
    }
}
