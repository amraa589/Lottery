package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.dto.request.LotteryRequest;
import mn.edu.num.lotteryProject.dto.response.LotteryResponse;
import mn.edu.num.lotteryProject.dto.response.WinnerResponse;
import mn.edu.num.lotteryProject.entity.Lottery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LotteryService {

    List<LotteryResponse> fetchLotteryList() throws Exception;

    LotteryResponse createLottery(LotteryRequest lotteryRequest, byte[] img) throws Exception;

    LotteryResponse deleteLottery(String id) throws Exception;

    Lottery getLotteryDetails(Long id) throws Exception;

    void saveImage(MultipartFile imageFile) throws Exception;

//    List<WinnerResponse> fetchLotteryWinners(int numberOfWinners, int numberOfLottery) throws Exception;

    WinnerResponse lotteryWinner(String lotteryId) throws Exception;
}
