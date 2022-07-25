package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.LoginRequest;
import mn.edu.num.lotteryProject.dto.UserRequest;
import mn.edu.num.lotteryProject.dto.UserResponse;
import mn.edu.num.lotteryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public List<UserResponse> fetchUserList() {
        List<UserResponse> response = userService.fetchUserList();
        return response;
    }

//    @GetMapping("/log-in")
//    public void configure (){
//
//    }

    @DeleteMapping("/{id}")
    public UserResponse fetchUserList(@PathVariable String id) {
        try {
            return userService.deleteUser(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest dto) throws ValidationException {
        try {
            return userService.login(dto);
        } catch (ValidationException e) {
            throw new ValidationException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/create")
    public UserResponse createUser(@RequestBody UserRequest userRequest, HttpServletRequest req) throws Exception {
        UserResponse response;
        response = userService.createUser(userRequest);
        return response;
    }
}
