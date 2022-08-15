package mn.edu.num.lotteryProject.service.impl;

import mn.edu.num.lotteryProject.dto.request.CustomerRequest;
import mn.edu.num.lotteryProject.dto.response.CustomerResponse;
import mn.edu.num.lotteryProject.entity.Customer;
import mn.edu.num.lotteryProject.repository.CustomerRepository;
import mn.edu.num.lotteryProject.service.CustomerService;
import mn.edu.num.lotteryProject.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) throws Exception{

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setRegistrationNumber(request.getRegistrationNumber());

        customer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse();

        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setRegistrationNumber(customer.getRegistrationNumber());

        return response;
    }

    public List<CustomerResponse> createCustomers(byte[] excel) throws Exception {
        Customer customer = new Customer();

        /*todo
        * excel ees unshaad hereglegchiig database ruu hadgaldag bolgoh heregtei
        * */


        customer = customerRepository.save(customer);
        return null;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Customer> customers = ExcelHelper.excelToCustomers(file.getInputStream());
            System.out.println(customers);
            customerRepository.saveAll(customers);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
    @Override
    public CustomerResponse deleteCustomer(String id) throws Exception {
        try {
            CustomerResponse response = new CustomerResponse();

            Optional<Customer> customer = customerRepository.findById(Long.parseLong(id));
            if (customer.isPresent()) {
                customerRepository.delete(customer.get());
                response.setId(customer.get().getId());
                return response;
            } else {
                throw new Exception("Customer does not exist");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
