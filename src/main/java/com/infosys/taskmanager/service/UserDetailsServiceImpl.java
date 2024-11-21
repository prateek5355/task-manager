//package com.infosys.taskmanager.service;
//
//import com.infosys.taskmanager.dto.AuthenticationResponse;
//import com.infosys.taskmanager.dto.LoginDto;
//import com.infosys.taskmanager.dto.RegisterDto;
//import com.infosys.taskmanager.entity.User;
//import com.infosys.taskmanager.enums.Role;
//import com.infosys.taskmanager.repository.UserRepository;
//import com.infosys.taskmanager.security.TokenManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class UserDetailsServiceImpl {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    TokenManager tokenManager;
//
//    @Autowired
//    private  AuthenticationManager authenticationManager;
//
//
//    public AuthenticationResponse registerUser(RegisterDto registerDto) {
//
//        var user= User.builder()
//                .firstName(registerDto.getFirstName())
//                .lastName(registerDto.getLastName())
//                .email(registerDto.getEmail())
//                .password(passwordEncoder.encode(registerDto.getPassword()))
//                .isEnabled(true)
//                .date(LocalDateTime.now())
//                .role(Role.valueOf(registerDto.getRole().toUpperCase()))
//                .build();
//        userRepository.save(user);
//        var jwtToken=tokenManager.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//
//    public AuthenticationResponse authenticate(LoginDto request) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userRepository.findByEmail(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        var jwtToken=tokenManager.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
//}
