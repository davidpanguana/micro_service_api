package com.ms.user_api.converter;

import com.ms.user_api.dto.UserDTO;
import com.ms.user_api.model.UserModel;

public class DTOConverter {

    public static UserDTO convert(UserModel userModel){

        return new UserDTO(
                userModel.getName(),
                userModel.getCpf(),
                userModel.getAddress(),
                userModel.getEmail(),
                userModel.getContact(),
                userModel.getRegistrationDate()
        );
    }
}
