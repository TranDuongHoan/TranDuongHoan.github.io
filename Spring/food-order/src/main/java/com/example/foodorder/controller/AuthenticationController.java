package com.example.foodorder.controller;

import com.example.foodorder.entity.RefreshToken;
import com.example.foodorder.entity.User;
import com.example.foodorder.exception.ExistedUserException;
import com.example.foodorder.exception.RefreshTokenNotFoundException;
import com.example.foodorder.model.request.LoginRequest;
import com.example.foodorder.model.request.RefreshTokenRequest;
import com.example.foodorder.model.request.RegistrationRequest;
import com.example.foodorder.model.request.UserRequest;
import com.example.foodorder.model.response.JwtResponse;
import com.example.foodorder.repository.RefreshTokenRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.security.CustomUserDetails;
import com.example.foodorder.security.JwtUtils;
import com.example.foodorder.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    JwtUtils jwtUtils;

    UserService userService;

    UserRepository userRepository;

    RefreshTokenRepository refreshTokenRepository;

    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword()));

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

        return JwtResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .id(userDetails.getId())
                .fullName(userDetails.getUsername())
                .roles(roles)
                .build();
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request) {
        return userRepository.findByUsername(request.getName())
                .map(user -> new ResponseEntity<>("Username is existed", HttpStatus.BAD_REQUEST))
                .orElseGet(() -> {
                    userService.registerUser(request);
                    return new ResponseEntity<>(null, HttpStatus.CREATED);
                });
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest request) throws ExistedUserException {
        userService.createUser(request);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        try {
            return ResponseEntity.ok(userService.refreshToken(request));
        } catch (RefreshTokenNotFoundException | UsernameNotFoundException ex) {
            return new ResponseEntity<>("Thông tin refreshToken không chính xác", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        userService.logout();
        return ResponseEntity.ok(null);
    }

}