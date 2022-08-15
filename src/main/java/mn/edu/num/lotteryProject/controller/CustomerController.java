package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.request.CustomerRequest;
import mn.edu.num.lotteryProject.dto.response.CustomerResponse;
import mn.edu.num.lotteryProject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public List<CustomerResponse> fetchCustomersList() {
//        List<CustomerResponse> responses = customerService.createCustomers(/*excel ogogdol oruulj ogoh */);
        return null;
    }

    @PostMapping("/create")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest dto) {
        try{
            return customerService.createCustomer(dto);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
