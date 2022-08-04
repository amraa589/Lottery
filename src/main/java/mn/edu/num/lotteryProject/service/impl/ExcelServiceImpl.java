package mn.edu.num.lotteryProject.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import mn.edu.num.lotteryProject.entity.User;
import mn.edu.num.lotteryProject.repository.UserRepository;
import mn.edu.num.lotteryProject.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelServiceImpl {
    @Autowired
    UserRepository repository;

    public void save(MultipartFile file) {
        try {
            List<User> tutorials = ExcelHelper.excelToUsers(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<User> users = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.usersToExcel(users);
        return in;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}