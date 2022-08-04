package mn.edu.num.lotteryProject.service;

import mn.edu.num.lotteryProject.dto.request.LoginRequest;
import mn.edu.num.lotteryProject.dto.request.UserRequest;
import mn.edu.num.lotteryProject.dto.response.UserResponse;
import mn.edu.num.lotteryProject.entity.User;

import java.util.List;


public interface UserService {
    List<UserResponse> fetchUserList();
    UserResponse createUser(UserRequest request) throws Exception;

    UserResponse deleteUser(String id) throws Exception;

    UserResponse login(LoginRequest dto) throws Exception;

    User getUserDetails(String username) throws Exception;

}
