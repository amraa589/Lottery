package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.LotteryRequest;
import mn.edu.num.lotteryProject.dto.LotteryResponse;
import mn.edu.num.lotteryProject.dto.UserResponse;
import mn.edu.num.lotteryProject.repository.LotteryRepository;
import mn.edu.num.lotteryProject.service.LotteryService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/lottery")
public class LotteryController {

    @Autowired
    LotteryService lotteryService;

    @GetMapping("/")
    public String goodBye(){
        return "Goodbye!";
    }
    @GetMapping("/list")
    public List<LotteryResponse> fetchLotteryList() throws Exception {
        List<LotteryResponse> response = lotteryService.fetchLotteryList();
        return response;
    }
    @PostMapping("/create")
    public String uploadImage(@ModelAttribute LotteryRequest dto, @RequestParam("imageFile") MultipartFile imageFile){
        String returnValue = "";
        try{
            byte[] image = Base64.encodeBase64(imageFile.getBytes());

            dto.setBanner(image);
            lotteryService.createLottery(dto);
        }
        catch (Exception e){
            e.printStackTrace();
            returnValue="error";
        }
        return returnValue;
    }

}
