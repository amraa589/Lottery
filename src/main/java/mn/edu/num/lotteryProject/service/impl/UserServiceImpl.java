package mn.edu.num.lotteryProject.service.impl;

import mn.edu.num.lotteryProject.dto.UserRequest;
import mn.edu.num.lotteryProject.dto.UserResponse;
import mn.edu.num.lotteryProject.entity.User;
import mn.edu.num.lotteryProject.repository.UserRepository;
import mn.edu.num.lotteryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserResponse> fetchUserList() {
        List<User> list = userRepository.findAll();
        List<UserResponse> response = new ArrayList<>();

        for(User user: list) {
            UserResponse tmp = new UserResponse();
            tmp.setEmail(user.getEmail());
            tmp.setFirstName(user.getFirstName());
            tmp.setLastName(user.getLastName());
            tmp.setUsername(user.getUserName());
            tmp.setId(user.getId());

            response.add(tmp);
        }

        return response;
    }

    @Override
    public UserResponse createUser(UserRequest request) {

           User user = new User();
           user.setEmail(request.getEmail());
           user.setFirstName(request.getFirstName());

           user = userRepository.save(user);

           UserResponse response = new UserResponse();
           response.setId(user.getId());
           return response;

    }

    @Override
    public UserResponse deleteUser(String id) throws Exception {
        try{
            UserResponse response = new UserResponse();

            Optional<User> user = userRepository.findById(Long.parseLong(id));
            if(user.isPresent()) {
                userRepository.delete(user.get());
                response.setId(user.get().getId());
                return response;
            } else {
                throw new Exception("User does not exist");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
