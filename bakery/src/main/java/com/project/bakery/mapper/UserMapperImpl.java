package com.project.bakery.mapper;

import com.project.bakery.entity.User;
import com.project.bakery.model.MRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper{
    @Override
    public MRegisterResponse toRegisterResponse(String email, String name) {
        MRegisterResponse response = new MRegisterResponse();
        response.setEmail(email);
        response.setName(name);
        return response;
    }
}
