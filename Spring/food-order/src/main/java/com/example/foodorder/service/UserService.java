package com.example.foodorder.service;

import com.example.foodorder.entity.Role;
import com.example.foodorder.entity.User;
import com.example.foodorder.exception.ExistedUserException;
import com.example.foodorder.exception.RefreshTokenNotFoundException;
import com.example.foodorder.model.request.*;
import com.example.foodorder.model.response.CommonResponse;
import com.example.foodorder.model.response.JwtResponse;
import com.example.foodorder.model.response.UserResponse;
import com.example.foodorder.model.response.UserSearchResponse;
import com.example.foodorder.repository.RefreshTokenRepository;
import com.example.foodorder.repository.RoleRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.repository.custom.UserCustomRepository;
import com.example.foodorder.security.CustomUserDetails;
import com.example.foodorder.security.JwtUtils;
import com.example.foodorder.security.SecurityUtils;
import com.example.foodorder.statics.Roles;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final PasswordEncoder passwordEncoder;

    final UserRepository userRepository;

    final RoleRepository roleRepository;

    final ObjectMapper objectMapper;

    final RefreshTokenRepository refreshTokenRepository;

    final UserCustomRepository userCustomRepository;

    @Value("${application.security.refreshToken.tokenValidityMilliseconds}")
    long refreshTokenValidityMilliseconds;

    final JwtUtils jwtUtils;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                       RoleRepository roleRepository, ObjectMapper objectMapper,
                       RefreshTokenRepository refreshTokenRepository, UserCustomRepository userCustomRepository, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userCustomRepository = userCustomRepository;
        this.jwtUtils = jwtUtils;
    }

    public User registerUser(RegistrationRequest registrationRequest) {
        Set<Role> roles = new HashSet<>();

        Optional<Role> optionalRole = roleRepository.findByName(Roles.USER);
        roles.add(optionalRole.get());

        User user = User.builder()
                .username(registrationRequest.getName())
                .phone(registrationRequest.getPhone())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }


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

    @Transactional
    public void logout() {
        Optional<Long> userIdOptional = SecurityUtils.getCurrentUserLoginId();
        if (userIdOptional.isEmpty()) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại");
        }
        refreshTokenRepository.logOut(userIdOptional.get());
        SecurityContextHolder.clearContext();
    }

    public void createUser(@Valid UserRequest request) throws ExistedUserException {
        Optional<User> userOptional = userRepository.findByUsername(request.getName());
        if (!userOptional.isEmpty()) {
            throw new ExistedUserException();
        }

        Set<Role> roles = roleRepository.findByName(Roles.USER).stream().collect(Collectors.toSet());

        User user = User.builder()
                .username(request.getName())
                .password(passwordEncoder.encode("123"))
                .phone(request.getPhone())
                .roles(roles)
                .build();
        userRepository.save(user);
    }

    public CommonResponse<?> searchUser(UserSearchRequest request) {
        List<UserSearchResponse> users = userCustomRepository.searchUser(request);

        Integer pageIndex = request.getPageIndex();
        Integer pageSize = request.getPageSize();
        double pageNumber = Math.ceil((float) users.size() / pageSize);

        users = users.subList((pageIndex - 1) * pageSize + 1, pageIndex * pageSize + 1);

        return CommonResponse.builder()
                .pageNumber((int) pageNumber)
                .data(users)
                .build();
    }

}
