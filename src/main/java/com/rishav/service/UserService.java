package com.rishav.service;

import com.rishav.model.User;

public interface UserService {
    User findByProfileByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long UserId) throws Exception;

    User updateUserProjectSize(User user, int number);
}
