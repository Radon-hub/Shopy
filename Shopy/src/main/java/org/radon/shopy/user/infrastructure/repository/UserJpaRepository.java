package org.radon.shopy.user.infrastructure.repository;

import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
    Optional<UserEntity> findUserByEmailOrPhoneNumber(String email, String phoneNumber);
    Optional<UserEntity> findUserByEmailAndPhoneNumber(String email, String phoneNumber);

    Optional<UserEntity> findUserEntitiesByPhoneNumberAndEmail(String phoneNumber, String email);

    Optional<UserEntity> findUserEntitiesByPhoneNumber(String phoneNumber);
}
