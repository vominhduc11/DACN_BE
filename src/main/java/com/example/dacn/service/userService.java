package com.example.dacn.service;

import com.example.dacn.entity.user;
import com.example.dacn.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class userService {
    @Autowired
    private userRepository userRepository;

    public user getUser(int idUser) {
        user user = userRepository.findById(idUser).get();

        return user;
    }
    public String addUser(String name, String email, String password, MultipartFile multipartFile) {
        // Kiểm tra tên đã tồn tại
        if (userRepository.existsByName(name)) {
            return "Tên người dùng đã tồn tại!";
        }
        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(email)) {
            return "Email đã tồn tại!";
        }
        // Lưu hình ảnh vào thư mục
        String imageUrl = saveImage(multipartFile);
        // Thêm người dùng mới vào bảng
        user user = new user();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setImage(imageUrl);
        // Lưu người dùng mới vào cơ sở dữ liệu
        userRepository.save(user);
        return "Success";
    }
    // Hàm lưu hình ảnh vào thư mục
    private String saveImage(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        // Đảm bảo thư mục upload tồn tại
        Path uploadPath = Paths.get("uploads/");
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lấy tên gốc của tệp
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // Lấy đường dẫn file
            Path filePath = uploadPath.resolve(fileName);

            // Lưu tệp lên hệ thống
            file.transferTo(filePath);

            // Trả về đường dẫn lưu trữ của hình ảnh
            return fileName.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
