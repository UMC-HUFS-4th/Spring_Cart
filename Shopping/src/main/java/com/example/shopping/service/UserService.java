package com.example.shopping.service;

import com.example.shopping.entity.User;
import com.example.shopping.entity.WishList;
import com.example.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final WishListService wishListService;

    @Autowired
    public UserService(UserRepository userRepository, WishListService wishListService) {
        this.userRepository = userRepository;
        this.wishListService = wishListService;
    }

    public User createUser(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setStatus("Active");
        user = userRepository.save(user);
        WishList wishList = wishListService.createWishList(user);
        user.setWishList(wishList);
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUser(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public User updateUser(Long user_id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (userDetails.getUsername() != null) {
                user.setUsername(userDetails.getUsername());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(userDetails.getPassword());
            }
            if (userDetails.getPhoneNumber() != null) {
                user.setPhoneNumber(userDetails.getPhoneNumber());
            }

            user.setModifiedDate(LocalDateTime.now());
            return user;
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }

    public void deleteUser(Long user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setModifiedDate(LocalDateTime.now());
            user.setStatus("Deleted");
            userRepository.save(user);
            userRepository.delete(user);
        } else {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
    }
}

