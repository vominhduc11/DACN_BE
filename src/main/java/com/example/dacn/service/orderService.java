package com.example.dacn.service;

import com.example.dacn.entity.order;
import com.example.dacn.repository.orderRepository;
import com.example.dacn.repository.productRepository;
import com.example.dacn.repository.userRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class orderService {
    @Autowired
    private orderRepository orderRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private productRepository productRepository;


    public List<Map<String, Object>> getAllOrder(int idUser) {
        // Tạo Gson instance
        Gson gson = new Gson();
        // Lấy tất cả đơn hàng từ dưới lên
        List<order> orders = orderRepository.findAllByUserIdOrderByIdDesc(idUser);

        List<Map<String, Object>> orderDTOs = new ArrayList<>();

        orders.forEach(order -> {
            Type listType = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
//             Parse JSON thành Map
            List<Map<String, Object>> map = gson.fromJson(order.getQuantity(), listType);
            Map<String, Object> orderDTO = new HashMap<>() {{
                put("id", order.getId());
                put("image", order.getProduct().getImage());
                put("name", order.getProduct().getName());
                put("name_package", order.getName_package());
                put("quantity", map);
            }};
            orderDTOs.add(orderDTO);
        });

        return orderDTOs;
    }

    public void addOrder(List<Map<String, Object>> datas, int idUser) {
        Gson gson = new Gson();
        // Tạo ra mảng đơn hàng rỗng ban đầu
        List<order> orders = new ArrayList<>();
        // Tạo ra mảng DTO và duyệt
        datas.forEach(data -> {
            boolean condition;
            String id;
            do {
                // Tạo UUID ngẫu nhiên
                UUID uuid = UUID.randomUUID();
                id = uuid.toString().substring(0, 10);
                // Kiểm tra xem id đó có tồn tại chưa;
                condition = orderRepository.existsById(id);
            } while (condition);
            // Chuyển quantity thành chuỗi json
            String s = gson.toJson(data.get("quantity"));

            order order = new order();
            order.setId(id);
            order.setName_package((String) data.get("name_package"));
            order.setUser(userRepository.findById(idUser).get());
            order.setProduct(productRepository.findById((int) data.get("id")).get());
            order.setQuantity(s);
            orders.add(order);
        });
        orderRepository.saveAll(orders);
    }

    // Chạy mỗi 30 phút
    @Scheduled(fixedRate = 30000) // 30 phút (mili giây)
    public void deleteExpiredData() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(1); // Lấy thời gian 2 tiếng trước
        orderRepository.deleteOldData(expirationTime);
    }
}
