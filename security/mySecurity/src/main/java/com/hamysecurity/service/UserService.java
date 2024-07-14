package com.hamysecurity.service;

import com.hamysecurity.dto.SignUpRequest;
import com.hamysecurity.model.User;

public interface UserService {
    void signup(SignUpRequest signUpRequest);
    Long getUserIdByUsername(String username);
    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    boolean deleteUser(Long id);
}
