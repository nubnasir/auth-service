package com.authentication.nubnasirauth.repository;

import com.authentication.nubnasirauth.model.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}
