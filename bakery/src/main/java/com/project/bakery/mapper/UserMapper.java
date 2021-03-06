package com.project.bakery.mapper;

import com.project.bakery.model.MRegisterLineResponse;
import com.project.bakery.model.MRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {
    MRegisterResponse toRegisterResponse(String email, String name);

    MRegisterLineResponse toRegisterLineResponse(String email, String tokenId, String name);
}
