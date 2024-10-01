package com.ms.user_api.controller;

import com.ms.user_api.dto.UserDTO;
import com.ms.user_api.model.UserModel;
import com.ms.user_api.repository.UserRepository;
import com.ms.user_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/User_API")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "save user", description = "Register user on dataBase", tags = "User_API")
    @PostMapping("/user")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserDTO userDTO){
        return userService.saveUser(userDTO);
    }

    @Operation(summary = "List all user", description = "List all usres who are registered on dataBase", tags = "User_API")
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getALL(){
        return userService.getAllUser();
    }

    @Operation(summary = "Search user by id", description = "Return only one user", tags = "User_API")
    @GetMapping("/user/{id}")
    public ResponseEntity<Object>getUserById(@PathVariable(value = "id") UUID id){
      return userService.getUserById(id);
    }

    @Operation(summary = "Update user", description = "update usr date", tags = "User_API")
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid UserDTO userDTO){

        return userService.updateUser(id, userDTO);
    }

    @Operation(summary = "Delete user", description = "given in id, delete all informetion about that user", tags = "User_API")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
      return userService.deleteUser(id);
    }

    @Operation(summary = "Search user by cpf", description = "Return only one user", tags = "User_API")
    @GetMapping("user/cpf/{cpf}")
    public ResponseEntity<UserDTO>getUsersByCpf(@PathVariable(value = "cpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByCpf(cpf));
    }

    @Operation(summary = "Search user by name", description = "Return only one user", tags = "User_API")
    @GetMapping("/user/search")
    public ResponseEntity<List<UserDTO>> queryByName(
            @RequestParam(name="name", required = true)
            String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.queryByName(name));
    }

}
