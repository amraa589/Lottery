package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.request.LotteryRequest;
import mn.edu.num.lotteryProject.dto.response.LotteryResponse;
import mn.edu.num.lotteryProject.dto.response.UserResponse;
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

    @GetMapping("/")
    public String goodBye() {
        return "Goodbye!";
    }

    @GetMapping("/list")
    public List<LotteryResponse> fetchLotteryList() throws Exception {
        List<LotteryResponse> response = lotteryService.fetchLotteryList();
        return response;
    }

//todo /* databased alotteryg uusgen zurag edrtei niiluulen hadgalah
// /

    @PostMapping("/create")
    public String createLottery(@ModelAttribute LotteryRequest dto, @RequestParam("imageFile") MultipartFile imageFile) {
        String returnValue = "";
        try {
            logger.info("Zurag encodelohoos omno");
            byte[] image = Base64.encodeBase64(imageFile.getBytes());
            logger.info("Zurag hiihiin daraa");
            logger.info(image.toString());
            dto.setBanner(image);
            lotteryService.createLottery(dto);
        } catch (Exception e) {
            e.printStackTrace();
            returnValue = "error";
        }
        return returnValue;
    }

    @DeleteMapping("/{id}")
    public LotteryResponse fetchLotteryList(@PathVariable String id) {
        try {
            return lotteryService.deleteLottery(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
