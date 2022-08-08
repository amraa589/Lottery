package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.dto.response.CustomerResponse;
import org.springframework.web.multipart.MultipartFile;


public interface CustomerService {
    CustomerResponse createCustomer(byte[] excel) throws Exception;

    void save(MultipartFile file);

    CustomerResponse deleteCustomer(String id) throws Exception;
}
