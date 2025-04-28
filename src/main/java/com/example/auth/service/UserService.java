package com.example.auth.service;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.JwtUtil;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(String mobile) {
        Optional<User> existingUser = userRepository.findByMobile(mobile);

        if (existingUser.isPresent()) {
            return "User already registered!";
        }

        String otp = generateOtp();
        User newUser = User.builder()
                .mobile(mobile)
                .otp(otp)
                .isVerified(false)
                .build();

        userRepository.save(newUser);
        return "OTP sent: " + otp; // In real application, send OTP via SMS.
    }

    public String verifyOtp(String mobile, String otp) {
        Optional<User> userOpt = userRepository.findByMobile(mobile);

        if (userOpt.isEmpty()) {
            return "User not found!";
        }

        User user = userOpt.get();

        if (user.getOtp().equals(otp)) {
            user.setIsVerified(true);
            userRepository.save(user);
            return jwtUtil.generateToken(mobile);
        } else {
            return "Invalid OTP!";
        }
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(999999));
    }
}
