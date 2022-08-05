package mn.edu.num.lotteryProject.service.impl;

import mn.edu.num.lotteryProject.dto.request.LotteryRequest;
import mn.edu.num.lotteryProject.dto.response.LotteryResponse;
import mn.edu.num.lotteryProject.entity.Lottery;
import mn.edu.num.lotteryProject.repository.LotteryRepository;
import mn.edu.num.lotteryProject.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    LotteryRepository lotteryRepository;


    @Override
    public List<LotteryResponse> fetchLotteryList() throws Exception{
        List<Lottery> list = lotteryRepository.findAll();
        List<LotteryResponse> response = new ArrayList<>();
        for(Lottery lottery : list) {
            LotteryResponse tmp = new LotteryResponse();
            tmp.setDescription(lottery.getDescription());
            tmp.setRunningDate(lottery.getRunningDate());
            tmp.setStartDate(lottery.getStartDate());
            tmp.setEndDate(lottery.getEndDate());
            tmp.setBanner(lottery.getBanner());
            tmp.setId(lottery.getId());
            response.add(tmp);
        }
        return response;
    }

    @Override
    public LotteryResponse createLottery(LotteryRequest dto) {

        Lottery lottery = new Lottery();

        lottery.setDescription(dto.getDescription());
        lottery.setEndDate(dto.getEndDate());
        lottery.setRunningDate(dto.getRunningDate());
        lottery.setStartDate(dto.getStartDate());
        lottery.setName(dto.getName());
        lottery.setNumberOfWinners(dto.getNumberOfWinners());

        lottery = lotteryRepository.save(lottery);

        LotteryResponse response = new LotteryResponse();

        response.setId(lottery.getId());
//        response.setBanner(lottery.getBanner());
        response.setDescription(lottery.getDescription());
        response.setEndDate(lottery.getEndDate());
        response.setRunningDate(lottery.getRunningDate());
        response.setStartDate(lottery.getStartDate());
        response.setName(lottery.getName());
        response.setNumberOfWinners(lottery.getNumberOfWinners());

        return response;
    }


    @Override
    public LotteryResponse deleteLottery(String id) throws Exception {
        try {
            LotteryResponse response = new LotteryResponse();

            Optional<Lottery> lottery = lotteryRepository.findById(Long.parseLong(id));
            if (lottery.isPresent()) {
                lotteryRepository.delete(lottery.get());
                response.setId(lottery.get().getId());
                return response;
            } else {
                throw new Exception("Lottery does not exist");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Lottery getLotteryDetails(Long id) throws Exception{
        Optional<Lottery> optional = lotteryRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new Exception("Lottery doesn't found");
    }

    @Override
    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = "/photos/";
        byte bytes[] = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path,bytes);
    }
}
