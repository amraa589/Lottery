package mn.edu.num.lotteryProject.controller;

import mn.edu.num.lotteryProject.config.JwtTokenUtil;
import mn.edu.num.lotteryProject.dto.request.LoginRequest;
import mn.edu.num.lotteryProject.dto.request.UserRequest;
import mn.edu.num.lotteryProject.dto.response.UserResponse;
import mn.edu.num.lotteryProject.service.impl.JwtUserDetailsServiceImpl;
import mn.edu.num.lotteryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/auth")
public class JwtAuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public UserResponse createAuthenticationToken(@RequestBody LoginRequest dto) throws Exception {
        return userService.login(dto);
    }

}