package com.quyen.hust.service.user;

import com.quyen.hust.repository.user.WishlistJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WishlistService {
    private final WishlistJpaRepository wishlistJpaRepository;

}
