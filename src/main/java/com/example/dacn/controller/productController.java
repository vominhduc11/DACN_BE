package com.example.dacn.controller;

import com.example.dacn.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class productController {

    @Autowired
    private productService productService;

    // Lấy 1 sản phẩm thông qua id của sản phẩm đó
    @GetMapping(value = "/getProduct/{id}/{idUser}")
    public Map<String, Object> getProduct(@PathVariable("id") int id, @PathVariable("idUser") int idUser) {
        Map<String, Object> product = productService.getProduct(id, idUser);
        return product;
    }

    // Lấy sản phẩm cho biết id thành phố và số lượng
    @GetMapping(value = "/getProductOfCity/{id}/{amount}/{idUser}")
    public List<Map<String, Object>> getProductOfCity(@PathVariable("id") int id, @PathVariable("amount") int amount, @PathVariable("idUser") int idUser) {
        List<Map<String, Object>> products = productService.getProductOfCity(id, amount, idUser);
        return products;
    }

    @GetMapping(value = "/getInTurnProductOfCity/{id}/{amount}")
    public List<Map<String, Object>> getInTurnProductOfCity(@PathVariable("id") int id, @PathVariable("amount") int amount) {
        List<Map<String, Object>> products = productService.getInTurnProductOfCity(id, amount);
        return products;
    }

    // Lấy tất cả các thể loại của sản phẩm của 1 thành phố không trùng nhau, Cho biết id thành phố
    @GetMapping(value = "/getAllCategoryOfCity/{id}")
    public Set<String> getAllCategoryOfCity(@PathVariable("id") int id) {
        Set<String> strings = productService.getAllCategoryOfCity(id);
        return strings;
    }

    // Lấy lần lượt 1 lúc 10 sản phẩm từ dưới lên
    @GetMapping(value = "/getInTurnProduct/{amountProduct}")
    public List<Map<String, Object>> getInTurnProduct(@PathVariable("amountProduct") int amountProduct) {
        List<Map<String, Object>> products = productService.getInTurnProduct(amountProduct);
        return products;
    }

    // Lấy nhiều nhất 10 sản phẩm theo tên thành phố và thẻ loại
    @GetMapping(value = "/getListProduct/{category}/{cityId}")
    public List<Map<String, Object>> getListProduct(@PathVariable("category") String category, @PathVariable("cityId") int cityId) {
        List<Map<String, Object>> products = productService.getListProduct(category, cityId);
        return products;
    }

    // Lấy tất cả sản phẩm của 1 thể loại nào đó
    @GetMapping(value = "/getAllProductOfCategory/{category}/{idUser}")
    public List<Map<String, Object>> getAllProductOfCategory(@PathVariable("category") String category, @PathVariable("idUser") int idUser) {
        List<Map<String, Object>> products = productService.getAllProductOfCategory(category, idUser);
        return products;
    }

    // Lấy tất cả sản phẩm của 1 thành phố nào đó nào đó
    @GetMapping(value = "/getAllProductOfCity/{cityId}/{idUser}")
    public List<Map<String, Object>> getAllProductOfCity(@PathVariable("cityId") int cityId, @PathVariable("idUser") int idUser) {
        List<Map<String, Object>> products = productService.getAllProductOfCity(cityId, idUser);
        return products;
    }

    // Lấy sản phẩm theo chuỗi
    @GetMapping(value = "/getListProductAccordingString/{debounce}")
    public List<Map<String,Object>> getListProductAccordingString(@PathVariable("debounce") String debounce) {
        List<Map<String,Object>> objects = productService.getListProductAccordingString(debounce);

        return objects;
    }

    // Tìm tất cả sản phẩm theo thể loại và tên thành phố
    @GetMapping(value = "/getAllProductOfCategoryAndCity/{category}/{nameCity}/{idUser}")
    public List<Map<String,Object>> getAllProductOfCategoryAndCity(@PathVariable("category") String category, @PathVariable("nameCity") String nameCity, @PathVariable("idUser") int idUser) {
        List<Map<String,Object>> products = productService.getAllProductOfCategoryAndCity(category,nameCity,idUser);

        return products;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping(value = "/addProduct")
    public int addProduct(@RequestBody Map<String, Object> data) {
        String name = (String) data.get("name");
        String image = (String) data.get("image");
        int star = (int) data.get("star");
        int evaluate = (int) data.get("evaluate");
        int booked = (int) data.get("booked");
        String notify = (String) data.get("notify");
        String place = (String) data.get("place");
        String highlight = (String) data.get("highlight");
        String information = (String) data.get("information");
        String category = (String) data.get("category");
        int city = (int) data.get("city");
        int country = (int) data.get("country");


       return productService.addProduct(name, image, star, evaluate, booked, notify, place, highlight, information, category, city, country);
    }
}
