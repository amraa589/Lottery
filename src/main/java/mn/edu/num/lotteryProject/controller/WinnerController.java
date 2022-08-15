package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.response.UserResponse;
import mn.edu.num.lotteryProject.dto.response.WinnerResponse;
import mn.edu.num.lotteryProject.repository.WinnerRepository;
import mn.edu.num.lotteryProject.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/winner")
public class WinnerController {

    @Autowired
    LotteryService lotteryService;

    @GetMapping("/list")
    public List<WinnerResponse> fetchWinnerList() throws Exception {
//        List<WinnerResponse> response = lotteryService.fetchLotteryWinners(1,7);
        return null;
    }
}
