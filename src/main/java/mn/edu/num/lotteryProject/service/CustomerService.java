package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.dto.request.CustomerRequest;
import mn.edu.num.lotteryProject.dto.response.CustomerResponse;
import mn.edu.num.lotteryProject.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest) throws Exception;
    List<CustomerResponse> createCustomers(byte[] excel) throws Exception;

    void save(MultipartFile file);

    CustomerResponse deleteCustomer(String id) throws Exception;
}
