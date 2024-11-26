package com.example.dacn.controller;

import com.example.dacn.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class orderController {
    @Autowired
    private orderService orderService;

    // Lấy tất cả đơn hàng từ dưới lên
    @GetMapping(value = "/getAllOrder/{idUser}")
    public List<Map<String, Object>> getAllOrder(@PathVariable("idUser") int idUser){
        List<Map<String, Object>> result = orderService.getAllOrder(idUser);
        return result;
    }

    // Thêm 1 đơn hàng
    @PostMapping(value = "/addOrder")
    public List<Map<String, Object>> addOrder(@RequestParam("idUser") int idUser, @RequestBody List<Map<String, Object>> datas){
        orderService.addOrder(datas, idUser);
        return datas;
    }
}
