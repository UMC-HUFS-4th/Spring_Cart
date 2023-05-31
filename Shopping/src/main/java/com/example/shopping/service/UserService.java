package com.example.shopping.service;

import com.example.shopping.entity.Item;
import com.example.shopping.entity.User;
import com.example.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        LocalDateTime now = LocalDateTime.now();
        user.setCreateDate(now);
        user.setModifiedDate(now);
        user.setStatus("Created");
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setName(userDetails.getName());
            user.setModifiedDate(LocalDateTime.now());
            return userRepository.save(user);}
    }

    public void deleteUser(Long id){
        User user = getUser(id);
        user.setModifiedDate(LocalDateTime.now());
        user.setStatus("Deleted");
        userRepository.save(user);
        userRepository.delete(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        } else{ throw new RuntimeException("User not fine with id: "+id);
        }
    }


    public List<Item> getWishListItems(Long id){
        User user = userRepository.findById(id).orElse(other null);
        if (user != null && user.getWishList()!= null){
            return user.getWishList().getItem();
        }
        return Collections.emptyList();
    }
}

