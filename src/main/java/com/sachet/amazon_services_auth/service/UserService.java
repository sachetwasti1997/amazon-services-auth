package com.sachet.amazon_services_auth.service;

import com.sachet.amazon_services_auth.model.Address;
import com.sachet.amazon_services_auth.model.LoginRequest;
import com.sachet.amazon_services_auth.model.User;
import com.sachet.amazon_services_auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public String saveUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (user.getAddresses() != null && !user.getAddresses().isEmpty())
            user.addUser(user.getAddresses());
        if (existingUser.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return jwtService.generateToken(userRepository.save(user));
        }
        throw new Exception("User with that email already exists, please try a different email");
    }

    public String userLogin(LoginRequest request) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isEmpty()) {
            throw new Exception("User with that email already exists, please try a different email");
        }
        return jwtService.generateToken(existingUser.get());
    }

    public Address addAddress(Long userId, Address address) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("Cannot find User");
        }
        User savedUser = user.get();
        if (savedUser.getAddresses() == null) {
            savedUser.setAddresses(new ArrayList<>());
        }
        savedUser.getAddresses().add(address);
        address.setUser(savedUser);
        userRepository.save(savedUser);
        return address;
    }
}
