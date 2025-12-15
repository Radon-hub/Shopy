package org.radon.shopy.auth.infrastructure.adapter;

import org.radon.shopy.auth.application.port.out.AuthRepository;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserExistException;
import org.radon.shopy.user.domain.model.Password;
import org.radon.shopy.user.domain.model.User;
import org.radon.shopy.user.infrastructure.adapter.mapper.UserMappers;
import org.radon.shopy.user.infrastructure.repository.UserJpaRepository;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthRepositoryImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User createUser(User user) {

        Optional<UserEntity> isExisted = userJpaRepository.findUserEntitiesByPhoneNumberAndEmail(user.getPhoneNumber().value(), user.getEmail().value());

        user.setPassword(new Password(passwordEncoder.encode(user.getPassword().value())));

        if(isExisted.isPresent()){
            throw new UserExistException();
        }

        return UserMappers.toDomain(userJpaRepository.save(UserMappers.toEntity(user)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> user;

        if(username.contains("@")){
            user = userJpaRepository.findByEmail(username);
        }else{
            user = userJpaRepository.findByPhoneNumber(username);
        }

        if(user.isEmpty())
            throw new UsernameNotFoundException("Username not found!");

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getPhoneNumber())
                .password(user.get().getPassword())
                .roles("USER")
                .disabled(false)
                .build();
    }


}
