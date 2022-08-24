package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.dto.request.LoginRequest;
import mn.edu.num.lotteryProject.dto.request.UserRequest;
import mn.edu.num.lotteryProject.dto.response.UserResponse;
import mn.edu.num.lotteryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserResponse signup(@RequestBody UserRequest dto) throws Exception {
        return userService.createUser(dto);
    }

    @DeleteMapping("/delete/{id}")
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

}
