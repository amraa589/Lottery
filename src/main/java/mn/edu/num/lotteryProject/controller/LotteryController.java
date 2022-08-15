package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.request.LotteryRequest;
import mn.edu.num.lotteryProject.dto.response.LotteryResponse;
import mn.edu.num.lotteryProject.dto.response.WinnerResponse;
import mn.edu.num.lotteryProject.service.CustomerService;
import mn.edu.num.lotteryProject.service.LotteryService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/lottery")
public class LotteryController {
    Logger logger = LoggerFactory.getLogger(LotteryController.class);
    @Autowired
    LotteryService lotteryService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String goodBye() {
        return "Goodbye!";
    }

    @GetMapping("/list")
    public List<LotteryResponse> fetchLotteryList() throws Exception {
        List<LotteryResponse> response = lotteryService.fetchLotteryList();
        return response;
    }

//    @PostMapping("/create")
//    public String createLottery(@ModelAttribute LotteryRequest dto, @RequestParam("imageFile") MultipartFile imageFile) {
//        String returnValue = "";
//        try {
//            byte[] image = Base64.encodeBase64(imageFile.getBytes());
////            byte[] customers = Base64.encodeBase64(customerFile.getBytes());
////                customerService.save(customerFile);
//
//            lotteryService.createLottery(dto, image);
//        } catch (Exception e) {
//            e.printStackTrace();
//            returnValue = "error";
//        }
//        return returnValue;
//    }

    @PostMapping("/create")
    public LotteryResponse createLottery(@ModelAttribute LotteryRequest dto, @RequestParam("imageFile") MultipartFile imageFile) throws Exception {
        try {
            byte[] image = Base64.encodeBase64(imageFile.getBytes());
//            byte[] customers = Base64.encodeBase64(customerFile.getBytes());
//                customerService.save(customerFile);
            return lotteryService.createLottery(dto, image);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Unable to create Lottery!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public LotteryResponse fetchLotteryList(@PathVariable String id) {
        try {
            return lotteryService.deleteLottery(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //    @RequestParam("customerFile") MultipartFile customerFile
    @GetMapping("/winner/{id}")
    public WinnerResponse fetchWinnerList(@PathVariable String id) {
        try {
            WinnerResponse winnerResponse = lotteryService.lotteryWinner(id);
            return winnerResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
