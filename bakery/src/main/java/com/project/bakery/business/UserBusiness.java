package com.project.bakery.business;

import com.project.bakery.entity.User;
import com.project.bakery.exception.BaseException;
import com.project.bakery.mapper.UserMapper;
import com.project.bakery.model.MRegisterRequest;
import com.project.bakery.model.MRegisterResponse;
import com.project.bakery.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserBusiness(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        userService.createUser(request.getEmail(), request.getPassword(), request.getName());
        return  userMapper.toRegisterResponse(request.getEmail(), request.getName());
    }

}
