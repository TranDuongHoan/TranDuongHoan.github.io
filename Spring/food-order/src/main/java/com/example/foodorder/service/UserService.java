package com.example.foodorder.service;

import com.example.foodorder.entity.Role;
import com.example.foodorder.entity.User;
import com.example.foodorder.exception.RefreshTokenNotFoundException;
import com.example.foodorder.model.request.RefreshTokenRequest;
import com.example.foodorder.model.request.UserRequest;
import com.example.foodorder.model.response.JwtResponse;
import com.example.foodorder.model.response.UserResponse;
import com.example.foodorder.repository.RefreshTokenRepository;
import com.example.foodorder.repository.RoleRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.security.CustomUserDetails;
import com.example.foodorder.security.JwtUtils;
import com.example.foodorder.statics.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    ObjectMapper objectMapper;

    RoleRepository roleRepository;

    RefreshTokenRepository refreshTokenRepository;

    JwtUtils jwtUtils;

    @Value("${application.security.refreshToken.tokenValidityMilliseconds}")
    private long refreshTokenValidityMilliseconds;

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            return users.stream().map(u -> objectMapper.convertValue(u, UserResponse.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public UserResponse getDetail(Long id) throws ClassNotFoundException {
        return userRepository.findById(id).map(u -> objectMapper.convertValue(u, UserResponse.class)).orElseThrow(ClassNotFoundException::new);
    }

    public UserResponse create(UserRequest request) {
        Optional<Role> roleOptional = roleRepository.findByName(Roles.USER.name());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password
        Set<Role> roles = new HashSet<>();
        roles.add(roleOptional.get());
        user.setRoles(roles);
        userRepository.save(user);

        return objectMapper.convertValue(user, UserResponse.class);
    }

    public JwtResponse refreshToken(RefreshTokenRequest request) throws RefreshTokenNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String newToken = userRepository.findById(userDetails.getId())
                .flatMap(user -> refreshTokenRepository.findByUserAndRefreshTokenAndInvalidated(user, request.getRefreshToken(), false)
                        .map(refreshToken -> {
                            LocalDateTime createdDateTime = refreshToken.getCreatedDateTime();
                            LocalDateTime expiryTime = createdDateTime.plusSeconds(refreshTokenValidityMilliseconds / 1000);
                            if (expiryTime.isBefore(LocalDateTime.now())) {
                                refreshToken.setInvalidated(true);
                                refreshTokenRepository.save(refreshToken);
                                return null;
                            }
                            return jwtUtils.generateJwtToken(authentication);
                        }))
                .orElseThrow(() -> new UsernameNotFoundException("Tài khoản không tồn tại"));

        if (newToken == null) {
            throw new RefreshTokenNotFoundException();
        }
        return JwtResponse.builder()
                .jwt(newToken)
                .build();
    }

}
