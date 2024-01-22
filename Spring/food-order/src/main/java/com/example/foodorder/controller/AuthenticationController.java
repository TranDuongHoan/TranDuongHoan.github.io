package com.example.foodorder.controller;

import com.example.foodorder.entity.RefreshToken;
import com.example.foodorder.entity.User;
import com.example.foodorder.exception.ExistedUserException;
import com.example.foodorder.exception.RefreshTokenNotFoundException;
import com.example.foodorder.exception.UnauthorizedException;
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
import com.example.foodorder.statics.UserStatus;
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
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest request) throws UnauthorizedException {
        //thực hiện quá trình xác thực qua username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword())
        );
        //cập nhật thông tin xác thực trong SecurityContextHolder để có thể truy cập từ bất kỳ nơi nào trong ứng dụng
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //lấy thông tin chi tiết về người dùng sau khi xác thực
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        //lấy thông tin người dùng từ CSDL để thêm vào đối tượng JwtResponse
        User user = userRepository.findById(userDetails.getId()).get();
        //kiểm tra trạng thái của user
        if (user != null && !user.getUserStatus().equals(UserStatus.ACTIVATED)) {
            throw new UnauthorizedException("Tài khoản chưa được kích hoạt");
        }
        //sử dụng jwtUtils để tạo ra JWT Token dựa trên thông tin xác thực
        String jwt = jwtUtils.generateJwtToken(authentication);
        //lấy danh sách các quyền của người dùng từ thông tin chi tiết người dùng
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        //tạo 1 refresh token mới sử dụng UUID
        String refreshToken = UUID.randomUUID().toString();
        //tạo 1 đối tượng refresh token để lưu thông tin về refresh token và người dùng liên quan
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .user(userRepository.findById(userDetails.getId()).get())
                .build();
        //lưu refresh token vào CSDL
        refreshTokenRepository.save(refreshTokenEntity);
        //trả về đối tượng JwtResponse
        return JwtResponse.builder()
                .jwt(jwt)
                .refreshToken(refreshToken)
                .id(userDetails.getId())
                .email(user.getEmail())
                .fullName(user.getUsername())
                .avatar(user.getAvatar())
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
