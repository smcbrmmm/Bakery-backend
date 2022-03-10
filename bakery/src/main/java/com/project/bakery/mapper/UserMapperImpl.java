package com.project.bakery.mapper;

import com.project.bakery.model.MRegisterLineResponse;
import com.project.bakery.model.MRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper{
    @Override
    public MRegisterResponse toRegisterResponse(String email, String name) {
        MRegisterResponse response = new MRegisterResponse();
//        response.setEmail(email);
//        response.setName(name);
//        response.se
        return response;
    }

    @Override
    public MRegisterLineResponse toRegisterLineResponse(String email, String tokenId, String name) {
        MRegisterLineResponse response = new MRegisterLineResponse();
        response.setEmail(email);
        response.setTokenId(tokenId);
        response.setName(name);
        return response;
    }
}
