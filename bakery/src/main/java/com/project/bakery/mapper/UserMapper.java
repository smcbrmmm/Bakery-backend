package com.project.bakery.mapper;

import com.project.bakery.entity.User;
import com.project.bakery.model.MRegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {
    MRegisterResponse toRegisterResponse(String email, String name);
}
