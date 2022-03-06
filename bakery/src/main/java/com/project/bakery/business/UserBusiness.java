package com.project.bakery.business;

import com.project.bakery.exception.BaseException;
import com.project.bakery.mapper.UserMapper;
import com.project.bakery.model.MRegisterLineRequest;
import com.project.bakery.model.MRegisterLineResponse;
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


//    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
//        userService.createUser(request.getEmail(), request.getPassword(), request.getName());
//        return  userMapper.toRegisterResponse(request.getEmail(), request.getName());
//    }
//
//    public MRegisterLineResponse registerByLine(MRegisterLineRequest request) throws BaseException {
//        userService.createUserByLine(request.getEmail(), request.getTokenId(), request.getName());
//        return userMapper.toRegisterLineResponse(request.getEmail(), request.getTokenId(), request.getName());
//    }

}
