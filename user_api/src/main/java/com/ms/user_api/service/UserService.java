package com.ms.user_api.service;

import com.ms.user_api.dto.UserDTO;
import com.ms.user_api.model.UserModel;
import com.ms.user_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<UserModel> saveUser(UserDTO userDTO){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }


    public ResponseEntity<List<UserDTO>> getAllUser(){

        List<UserModel> users = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList()));
    }

    public ResponseEntity<Object>getUserById( UUID idUser){
        Optional<UserModel> userObject = userRepository.findById(idUser);
        if(userObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userObject.get());
    }

    public ResponseEntity<Object> updateUser(UUID id, UserDTO userDTO){
        Optional<UserModel> userObject = userRepository.findById(id);
        if(userObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        var userModel = userObject.get();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(userModel));
    }

    public ResponseEntity<Object> deleteUser( UUID idUser){
        Optional<UserModel> userObject = userRepository.findById(idUser);
        if(userObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        userRepository.delete(userObject.get());
        return ResponseEntity.status(HttpStatus.OK).body("user delected sucessfully");
    }

    @GetMapping("/user/{cpf}")
    public UserDTO findByCpf(String cpf){
        UserModel user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<UserModel> usuarios = userRepository.queryByNameLike(name);
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO getUserByCpf(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/cpf/" + cpf;
        ResponseEntity<UserDTO> response =
                restTemplate.getForEntity(url, UserDTO.class);
        return response.getBody();
    }

}
