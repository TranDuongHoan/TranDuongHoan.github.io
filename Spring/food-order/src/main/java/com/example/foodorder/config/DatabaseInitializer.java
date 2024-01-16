package com.example.foodorder.config;

import com.example.foodorder.entity.Role;
import com.example.foodorder.entity.User;
import com.example.foodorder.repository.RoleRepository;
import com.example.foodorder.repository.UserRepository;
import com.example.foodorder.statics.Roles;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DatabaseInitializer implements CommandLineRunner {

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Optional<Role> userRoleOptional = roleRepository.findByName(Roles.USER);
        if (userRoleOptional.isEmpty()) {
            Role userRole = Role.builder().name(Roles.USER).build();
            roleRepository.save(userRole);

            User hoan = userRepository.findByPhone("0915814554");
            if (ObjectUtils.isEmpty(hoan)) {
                User user2 = new User();
                user2.setPhone("0915814554");
                user2.setUsername("Hoan");
                user2.setPassword(passwordEncoder.encode("hoan")); // Encrypt the password
                Set<Role> roles2 = new HashSet<>();
                roles2.add(userRole);
                user2.setRoles(roles2);
                userRepository.save(user2);
            }
        }
        Optional<Role> adminRoleOptional = roleRepository.findByName(Roles.ADMIN);
        if (adminRoleOptional.isEmpty()) {
            Role adminRole = Role.builder().name(Roles.ADMIN).build();
            roleRepository.save(adminRole);

            User admin = userRepository.findByPhone("0912345678");
            if (ObjectUtils.isEmpty(admin)) {
                User user1 = new User();
                user1.setPhone("0912345678");
                user1.setUsername("admin");
                user1.setPassword(passwordEncoder.encode("admin")); // Encrypt the password
                Set<Role> roles1 = new HashSet<>();
                roles1.add(adminRole);
                user1.setRoles(roles1);
                userRepository.save(user1);
            }
        }

    }
}
