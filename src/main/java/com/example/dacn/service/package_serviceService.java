package com.example.dacn.service;

import com.example.dacn.entity.package_service;
import com.example.dacn.entity.product;
import com.example.dacn.entity.quantity;
import com.example.dacn.repository.package_serviceRepository;
import com.example.dacn.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class package_serviceService {
    @Autowired
    private productRepository productRepository;

    @Autowired
    private package_serviceRepository package_serviceRepository;

    public void addPackage(List<Map<String, Object>> data) {
        List<package_service> package_services = new ArrayList<>();

        for (Map<String, Object> data1 : data) {
            Optional<product> productOption = productRepository.findById((int) data1.get("idProduct"));
            product product = productOption.get();

            package_service packageService = new package_service();
            packageService.setName((String) data1.get("name"));
            packageService.setProduct(product);

            List<Map<String, Object>> quantityDataList = (List<Map<String, Object>>) data1.get("quantitys");

            List<quantity> quantities = new ArrayList<>();
            for (Map<String, Object> quantityData : quantityDataList) {
                quantity quantity = new quantity();
                quantity.setAge((String) quantityData.get("age"));
                quantity.setPrice(BigDecimal.valueOf((int) quantityData.get("price")));

                // Thiết lập packageService cho mỗi quantity
                quantity.setPackage_service(packageService);
                quantities.add(quantity);
            }

            packageService.setQuantitys(quantities);

            package_services.add(packageService);
        }

        package_serviceRepository.saveAll(package_services);
    }
}
