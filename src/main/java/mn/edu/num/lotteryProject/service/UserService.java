package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.dto.UserRequest;
import mn.edu.num.lotteryProject.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserResponse> fetchUserList();
    UserResponse createUser(UserRequest request);

    UserResponse deleteUser(String id) throws Exception;
}
