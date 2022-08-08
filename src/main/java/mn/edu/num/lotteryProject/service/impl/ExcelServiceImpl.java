package mn.edu.num.lotteryProject.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import mn.edu.num.lotteryProject.entity.Customer;
import mn.edu.num.lotteryProject.entity.User;
import mn.edu.num.lotteryProject.repository.CustomerRepository;
import mn.edu.num.lotteryProject.repository.UserRepository;
import mn.edu.num.lotteryProject.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void save(MultipartFile file) {
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Customer> customers = customerRepository.findAll();

        ByteArrayInputStream in = ExcelHelper.usersToExcel(customers);
        return in;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}