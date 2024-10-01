package com.ms.user_api.repository;

import com.ms.user_api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByCpf(String cpf);

    List<UserModel> queryByNameLike(String name);
}
