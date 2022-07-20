package mn.edu.num.lotteryProject.service.impl;

import mn.edu.num.lotteryProject.dto.LoginRequest;
import mn.edu.num.lotteryProject.dto.UserRequest;
import mn.edu.num.lotteryProject.dto.UserResponse;
import mn.edu.num.lotteryProject.entity.User;
import mn.edu.num.lotteryProject.repository.UserRepository;
import mn.edu.num.lotteryProject.utils.PasswordEncoder;
import mn.edu.num.lotteryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
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
        for (User user : list) {
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
    public UserResponse createUser(UserRequest request) throws Exception {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setUserName(request.getUsername());

        PasswordEncoder encoder =new PasswordEncoder();
        List<String> password = encoder.encrypt(request.getPassword());
        user.setPassword(password.get(0));

        user = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        return response;
    }

    @Override
    public UserResponse deleteUser(String id) throws Exception {
        try {
            UserResponse response = new UserResponse();

            Optional<User> user = userRepository.findById(Long.parseLong(id));
            if (user.isPresent()) {
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

    @Override
    public UserResponse login(LoginRequest dto) throws Exception {
        Optional<User> optional = userRepository.findByUserName(dto.getUsername());
        if(optional.isPresent()) {
            User user = optional.get();
            String password = user.getPassword();
            /*
            * @todo Check password
            * */
//            String p = new PasswordEncoder().decrypt(dto.getPassword(), user.getSalt());
//            System.out.println(p);
//            System.out.println(password);
//            if(p != password) {
//                throw new Exception("Incorrect password");
//            }
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setUsername(user.getUserName());
            /**
             * @todo Generate JWT
             */

            response.setJwt("asdjkasdkaklsdalskasdaskjdaksd");

            return new UserResponse();

        } else {
            throw new Exception("User does not exist");
        }
    }
//    public void hashWithSalt(String username, String password){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = bCryptPasswordEncoder.encode(password);
//    }
////
////    @Override
////    public void hashPassword(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
////        SecureRandom secureRandom = new SecureRandom();
////        //make sure to save this into a database
////        byte[] salt = secureRandom.generateSeed(12);
////
////        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, 10, 512);
////        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
////        byte[] hash = skf.generateSecret(pbeKeySpec).getEncoded();
////
////        //converting to string to store into database
////        String base64Hash = Base64.getMimeEncoder().encodeToString(hash);
////
////        User user = new User();
////        user.setHash(base64Hash);
////        user.setSalt(new String(salt));
////
////    }
}
