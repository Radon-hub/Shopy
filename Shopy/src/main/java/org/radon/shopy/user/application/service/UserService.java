package org.radon.shopy.user.application.service;


import org.radon.shopy.shared.aop.exceptionHandling.model.*;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.user.application.port.in.*;
import org.radon.shopy.user.application.port.out.UserRepository;
import org.radon.shopy.user.domain.model.ResetPassword;
import org.radon.shopy.user.domain.model.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements GetUserUseCase,GetUserByEmailOrPhoneUseCase, GetAllUsersUseCase, EditUserUseCase, EditPasswordUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public PagedResponse<User> getAllUsers(Pageable pageable) throws UserNotFoundException {
        return userRepository.getAllUsers(pageable);
    }

    @Cacheable(value = "user",key = "#phoneNumber")
    @Override
    public User getUserByEmailOrPhoneNumber(String email, String phoneNumber) throws UserNotFoundException {
        return userRepository.getUserByEmailOrPhoneNumber(email, phoneNumber);
    }

    @CachePut(value = "user",key = "#user.phoneNumber")
    @Override
    public User editUser(User user) throws UserNotFoundException {
        return userRepository.editUser(user);
    }

    @Override
    public String editPassword(ResetPassword resetPassword) throws CredentialException,UserNotFoundException, PasswordNotMatchException, PasswordOldNotMatchException, PasswordCanNotBeSameException {
        return  userRepository.editPassword(resetPassword);
    }

    @Cacheable(value = "user")
    @Override
    public User getUser() {
        return userRepository.getUser();
    }
}
