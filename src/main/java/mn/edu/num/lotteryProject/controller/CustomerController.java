package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.request.CustomerRequest;
import mn.edu.num.lotteryProject.dto.response.CustomerResponse;
import mn.edu.num.lotteryProject.service.CustomerService;
import mn.edu.num.lotteryProject.service.impl.ExcelServiceImpl;
import mn.edu.num.lotteryProject.utils.ExcelHelper;
import mn.edu.num.lotteryProject.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ExcelServiceImpl fileService;

    @GetMapping("/list")
    public List<CustomerResponse> fetchCustomersList() {
        List<CustomerResponse> responses = customerService.fetchCustomerList();
        return responses;
    }

    @PostMapping("/create")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest dto) {
        try {
            return customerService.createCustomer(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public CustomerResponse deleteCustomer(@PathVariable String id){
        try{
            return customerService.deleteCustomer(id);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    

    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "customer.xlsx";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }
}
