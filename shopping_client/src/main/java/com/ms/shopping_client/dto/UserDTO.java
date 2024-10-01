package com.ms.shopping_client.dto;

import com.ms.user_api.model.UserModel;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String name,
                      @NotBlank String cpf,
                      @NotBlank String address,
                      @NotBlank String email,
                      @NotBlank String contact,
                      @NotBlank String registrationDate) {

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
