package com.ms.user_api.model;

import com.ms.user_api.dto.UserDTO;
import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String contact;
    private String registrationDate;

    public static UserModel convert(UserDTO userDTO) {
        UserModel user = new UserModel();
        // Corrigindo os métodos de acesso
        user.setName(userDTO.name());
        user.setCpf(userDTO.cpf());
        user.setAddress(userDTO.address());
        user.setEmail(userDTO.email());
        user.setContact(userDTO.contact());
        user.setRegistrationDate(userDTO.registrationDate());
        return user;
    }


    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
