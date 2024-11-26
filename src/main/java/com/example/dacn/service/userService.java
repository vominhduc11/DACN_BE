package com.example.dacn.service;

import com.example.dacn.entity.user;
import com.example.dacn.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    public user getUser(int idUser) {
        user user = userRepository.findById(idUser).get();

        return user;
    }
    public String addUser(user user) {
            // Kiểm tra tên đã tồn tại
            if (userRepository.existsByName(user.getName())) {
                return "Tên người dùng đã tồn tại!";
            }

            // Kiểm tra email đã tồn tại
            if (userRepository.existsByEmail(user.getEmail())) {
                return "Email đã tồn tại!";
            }


            // Lưu người dùng mới vào cơ sở dữ liệu
            userRepository.save(user);
            return "Success";
    }

    public Integer authenAccount(String email, String password) {
        // Kiểm tra nếu có user với email và password đúng
        if (userRepository.existsByEmail(email) && userRepository.existsByPassword(password)) {
            // Lấy user từ cơ sở dữ liệu dựa trên email và password
            user user = userRepository.findByEmailAndPassword(email, password);

            // Nếu user tồn tại, trả về id của user
            if (user != null) {
                return user.getId(); // Giả sử bạn có phương thức getId() trong đối tượng User
            }
        }
        // Nếu không có user nào hoặc không đúng email/password, trả về null
        return null;
    }
}
