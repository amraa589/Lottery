package mn.edu.num.lotteryProject.service.impl;

import mn.edu.num.lotteryProject.dto.request.LotteryRequest;
import mn.edu.num.lotteryProject.dto.response.LotteryResponse;
import mn.edu.num.lotteryProject.dto.response.WinnerResponse;
import mn.edu.num.lotteryProject.entity.Customer;
import mn.edu.num.lotteryProject.entity.CustomerId;
import mn.edu.num.lotteryProject.entity.Lottery;
import mn.edu.num.lotteryProject.entity.Winner;
import mn.edu.num.lotteryProject.repository.CustomerRepository;
import mn.edu.num.lotteryProject.repository.LotteryRepository;
import mn.edu.num.lotteryProject.repository.WinnerRepository;
import mn.edu.num.lotteryProject.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    LotteryRepository lotteryRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    WinnerRepository winnerRepository;
    @Override
    public List<LotteryResponse> fetchLotteryList() throws Exception {
        List<Lottery> list = lotteryRepository.findAll();
        List<LotteryResponse> response = new ArrayList<>();
        for (Lottery lottery : list) {
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
    public LotteryResponse createLottery(LotteryRequest dto, byte[] img) {

        Lottery lottery = new Lottery();

        lottery.setDescription(dto.getDescription());
        lottery.setBanner(img);
        lottery.setEndDate(dto.getEndDate());
        lottery.setRunningDate(dto.getRunningDate());
        lottery.setStartDate(dto.getStartDate());
        lottery.setName(dto.getName());
        lottery.setNumberOfWinners(dto.getNumberOfWinners());

        lottery = lotteryRepository.save(lottery);

        LotteryResponse response = new LotteryResponse();

        response.setId(lottery.getId());
        response.setBanner(lottery.getBanner());
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
    public Lottery getLotteryDetails(Long id) throws Exception {
        Optional<Lottery> optional = lotteryRepository.findById(id);
        if (optional.isPresent()) return optional.get();
        throw new Exception("Lottery doesn't found");
    }

    @Override
    public void saveImage(MultipartFile imageFile) throws Exception {
        String folder = "/photos/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }

//    @Override
//    public List<WinnerResponse> fetchLotteryWinners(int numberOfWinners, int numberOfLottery) throws Exception {
//        List<Integer> winningLotteryIds = new ArrayList<>();
//        Random random = new Random();
//        for (int i = 0; i < numberOfWinners; i++) {
//            while (true) {
//                int winningLotteryId = random.nextInt(numberOfLottery) + 1;
//                if (!winningLotteryIds.contains(winningLotteryId)) {
//                    winningLotteryIds.add(winningLotteryId);
//                    break;
//                }
//            }
//        }
//        List<Customer> customers = customerRepository.findAll();
//
//        Lottery lottery = new Lottery();
//        List<Integer> randomCustomerIds = new ArrayList<>();
//
//        int totWinners = lottery.numberOfWinners;
//        int currentWinners = (int)winnerRepository.count();
//        int idx=0;
//
//        while(totWinners >= currentWinners){
//
//            if(lottery.endDate.getTime() > lottery.runningDate.getTime()){
//                if(totWinners >= currentWinners){
//                    int length = (int)customerRepository.count();
//
//                    while(true){
//                        int winningCustomerId = random.nextInt(length) + 1;
//                        if(!randomCustomerIds.contains(winningCustomerId)){
//                            randomCustomerIds.add(winningCustomerId);
//                            break;
//                        }
//                    }
//
//                    customerRepository.findById(randomCustomerIds.get(idx).longValue());
//                    idx++;
//                    Customer customer = new Customer();
//                    customer.setRegistrationNumber(customers.get(idx).getRegistrationNumber());
//                    customer.setLastName(customers.get(idx).getLastName());
//                    customer.setFirstName(customers.get(idx).getFirstName());
//                    customer.setPhoneNumber(customers.get(idx).getPhoneNumber());
//
//                    Winner winner = new Winner();
//                    winner.setFirstName(customer.getFirstName());
//                    winner.setLastName(customer.getLastName());
//                    // Customer deer username nemeh shaardlagatai eseh esvel winner ees username iig hasah heregtei yu
////        winner.setUserName();
//                    winner.setPhoneNumber(customer.getPhoneNumber());
//                    winner.setRegistrationNumber(customer.getRegistrationNumber());
//
//                    winnerRepository.save(winner);
//
//                    //String rndStr = yourArray[new Random().nextInt(yourArray.length)];
//
//                }
//                else{
//                    throw new Exception("Total winner number reached");
//                }
//
//            }
//            else{
//                throw new Exception("Lottery is expired!");
//            }
//            currentWinners++;
//        }
//        //Total winner number to be reached
//
////        WinnerResponse winnerResponse = new WinnerResponse();
////        winnerResponse.setFirstName(winner.getFirstName());
////        winnerResponse.setLastName(winner.getLastName());
////        winnerResponse.setUserName(winner.getUserName());
////        winnerResponse.setRegistrationNumber(winner.getRegistrationNumber());
////        winnerResponse.setPhoneNumber(winner.getPhoneNumber());
//
//        List<Winner> winners = winnerRepository.findAll();
//
//        List<WinnerResponse> response = new ArrayList<>();
//
//        winners.forEach((winner1) -> {
//
//            WinnerResponse tmp = new WinnerResponse();
//
//            tmp.setFirstName(winner1.getFirstName());
//            tmp.setLastName(winner1.getLastName());
//            tmp.setPhoneNumber(winner1.getPhoneNumber());
//            tmp.setRegistrationNumber(winner1.getRegistrationNumber());
//
//            response.add(tmp);
//        });
//
//        return response;
//    }

    private WinnerResponse generateWinner(Lottery lottery) {
        List<CustomerId> ids = customerRepository.findAllById();
        Random random = new Random();
        int index = random.nextInt(ids.size());
        Long customerId = ids.get(index).getId();

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();
        Winner winner = new Winner();
        winner.setLottery(lottery);
        winner.setFirstName(customer.getFirstName());
        winner.setLastName(customer.getLastName());
        winner.setRegistrationNumber(customer.getRegistrationNumber());
        winner.setPhoneNumber(customer.getPhoneNumber());

        winnerRepository.save(winner);
        WinnerResponse response= new WinnerResponse();
        /*
        @TODO
        Map fields
         */
//        response.setLotteryId();
        response.setId(winner.getId());
        response.setFirstName(winner.getFirstName());
        response.setRegistrationNumber(winner.getRegistrationNumber());
        response.setPhoneNumber(winner.getPhoneNumber());
        response.setLastName(winner.getLastName());
        return response;
    }

    @Override
    public WinnerResponse lotteryWinner(String lotteryId) throws Exception {
       Optional<Lottery> optionalLottery = lotteryRepository.findById(Long.parseLong(lotteryId));
       if(optionalLottery.isPresent()) {
           Lottery lottery = optionalLottery.get();
           long winners  = winnerRepository.countWinnersByLotteryId(Long.parseLong(lotteryId));

           Date today = new Date();
           if(lottery.endDate.getTime() > today.getTime() && today.getTime() < lottery.runningDate.getTime()); else throw new Exception("Lottery has expired");
           if(winners >= lottery.getNumberOfWinners()) throw new Exception("Lottery winners exceeded");
           WinnerResponse response = generateWinner(lottery);

        return response;
       } else {
           throw new Exception("Lottery not found");
       }
    }
}
