package org.radon.shopy.user.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopy.auth.application.port.in.GetCurrentUserUseCase;
import org.radon.shopy.shared.aop.exceptionHandling.model.*;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.user.application.port.out.UserRepository;
import org.radon.shopy.user.domain.model.ResetPassword;
import org.radon.shopy.user.domain.model.User;
import org.radon.shopy.user.infrastructure.adapter.mapper.UserMappers;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;
import org.radon.shopy.user.infrastructure.repository.UserJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final GetCurrentUserUseCase getCurrentUserUseCase;


    public UserRepositoryImpl(UserJpaRepository userJpaRepository, PasswordEncoder passwordEncoder, GetCurrentUserUseCase getCurrentUserUseCase) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }


    @Override
    public User getUser() {
        String currentUser = getCurrentUserUseCase.getCurrentUser();

        Optional<UserEntity> user;

        if(currentUser.contains("@")){
            user = userJpaRepository.findByEmail(currentUser);
        }else{
            user = userJpaRepository.findByPhoneNumber(currentUser);
        }

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        UserEntity userEntity = user.get();

        if (!Objects.equals(userEntity.getPhoneNumber(), currentUser)) {
            throw new AccessDeniedException("Access denied");
        }
        return UserMappers.toDomain(userEntity);
    }

    @Override
    @Transactional
    @PreAuthorize("#user.phoneNumber == authentication.name")
    public User editUser(User user) {

        Optional<UserEntity> userEntity = userJpaRepository.findByPhoneNumber(user.getPhoneNumber().value());

        if(userEntity.isEmpty()){
            throw new UserNotFoundException();
        }

        val entity = userEntity.get();

        entity.setAge(user.getAge().value());
        entity.setEmail(user.getEmail().value());
        entity.setFullName(user.getFullName().value());

        return UserMappers.toDomain(entity);
    }

    @Override
    public PagedResponse<User> getAllUsers(Pageable pageable) {

        Page<User> userList = userJpaRepository.findAll(pageable).map(UserMappers::toDomain);


        if(userList.isEmpty()){
            throw new UserNotFoundException();
        }

        return new PagedResponse<User>(
                userList.getContent(),
                userList.getNumber(),
                userList.getSize(),
                userList.getTotalPages(),
                userList.getTotalElements()
        );

    }

    @Override
    public User getUserByEmailOrPhoneNumber(String email, String phoneNumber) {
        Optional<UserEntity> isExisted;

        if(!email.isEmpty() && !phoneNumber.isEmpty()){
            isExisted = userJpaRepository.findUserByEmailAndPhoneNumber(email,phoneNumber);
        }else{
            isExisted = userJpaRepository.findUserByEmailOrPhoneNumber(email,phoneNumber);
        }

        if(isExisted.isEmpty()){
            throw new UserNotFoundException();
        }

        UserEntity userEntityModel = isExisted.get();

        return UserMappers.toDomain(userEntityModel);

    }

    @Override
    @Transactional
    @PreAuthorize("#resetPassword.phoneNumber == authentication.name")
    public String editPassword(ResetPassword resetPassword) {

        resetPassword.validatePasswords();

        Optional<UserEntity> isExisted = userJpaRepository.findByPhoneNumber(resetPassword.getPhoneNumber());

        if(isExisted.isEmpty()){
            throw new UserNotFoundException();
        }

        UserEntity userEntityModel = isExisted.get();

        if(!passwordEncoder.matches(resetPassword.getOldPassword(), userEntityModel.getPassword())){
            throw new PasswordOldNotMatchException();
        }

        if(passwordEncoder.matches(resetPassword.getNewPassword(), userEntityModel.getPassword())){
            throw new PasswordCanNotBeSameException();
        }


        resetPassword.setNewPassword(passwordEncoder.encode(resetPassword.getNewPassword()));
//        resetPassword.setNewPasswordConfirm(passwordEncoder.encode(resetPassword.getNewPasswordConfirm()));
//        resetPassword.setOldPassword(passwordEncoder.encode(resetPassword.getOldPassword()));

        userEntityModel.setPassword(resetPassword.getNewPassword());

        return "Your password has been changed.";
    }

}
