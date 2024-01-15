package com.example.foodorder.controller;

import com.example.foodorder.entity.RefreshToken;
import com.example.foodorder.entity.User;
import com.example.foodorder.exception.RefreshTokenNotFoundException;
import com.example.foodorder.model.request.LoginRequest;
import com.example.foodorder.model.request.RefreshTokenRequest;
import com.example.foodorder.model.response.JwtResponse;
import com.example.foodorder.repository.RefreshTokenRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.security.CustomUserDetails;
import com.example.foodorder.security.JwtUtils;
import com.example.foodorder.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {
    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        String refreshToken = UUID.randomUUID().toString();
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .user(userRepository.findById(userDetails.getId()).get())
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        User user = userRepository.findById(userDetails.getId()).get();

        return JwtResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .id(userDetails.getId())
                .email(userDetails.getUsername())
                .fullName(user.getUsername())
                .avatar(user.getAvatar())
                .roles(roles)
                .build();
    }



    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        try {
            return ResponseEntity.ok(userService.refreshToken(request));
        } catch (RefreshTokenNotFoundException | UsernameNotFoundException ex) {
            return new ResponseEntity<>("Thông tin refreshToken không chính xác", HttpStatus.BAD_REQUEST);
        }
    }





}
