package org.radon.shopy.user.presentation.controller;


import lombok.val;
//import org.radon.shopy.integration.vendor.application.port.in.GetVendorUseCase;
//import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.aop.exceptionHandling.model.*;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.shared.dto.Response;
import org.radon.shopy.user.application.port.in.EditPasswordUseCase;
import org.radon.shopy.user.application.port.in.EditUserUseCase;
import org.radon.shopy.user.application.port.in.GetAllUsersUseCase;
import org.radon.shopy.user.application.port.in.GetUserByEmailOrPhoneUseCase;
import org.radon.shopy.user.domain.model.User;
import org.radon.shopy.user.infrastructure.adapter.mapper.PasswordsMapper;
import org.radon.shopy.user.infrastructure.adapter.mapper.UserMappers;
import org.radon.shopy.user.presentation.dto.ResetPasswordRequest;
import org.radon.shopy.user.presentation.dto.UserEditRequest;
import org.radon.shopy.user.presentation.dto.UserResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final GetAllUsersUseCase getAllUsersUseCase;
    private final GetUserByEmailOrPhoneUseCase getUserByEmailOrPhoneUseCase;
    private final EditUserUseCase editUserUseCase;
    private final EditPasswordUseCase editPasswordUseCase;

    public UserController(GetAllUsersUseCase getAllUsersUseCase, GetUserByEmailOrPhoneUseCase getUserByEmailOrPhoneUseCase, EditUserUseCase editUserUseCase, EditPasswordUseCase editPasswordUseCase) {
        this.getAllUsersUseCase = getAllUsersUseCase;
        this.getUserByEmailOrPhoneUseCase = getUserByEmailOrPhoneUseCase;
        this.editUserUseCase = editUserUseCase;
        this.editPasswordUseCase = editPasswordUseCase;
    }

    @GetMapping("all")
    public ResponseEntity<Response<PagedResponse<UserResponse>>> findAll(
            Pageable pageable
    ) throws UserNotFoundException {
        val pagedResponse = getAllUsersUseCase.getAllUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response<>(
                       UserMappers.toPagedResponse(pagedResponse)
                )
        );
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Response<String>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws CredentialException,UserNotFoundException, PasswordOldNotMatchException, PasswordNotMatchException, PasswordCanNotBeSameException {
        return ResponseEntity.status(HttpStatus.OK).body(
                new Response<>(
                    editPasswordUseCase.editPassword(PasswordsMapper.fromResetPasswordRequest(resetPasswordRequest))
                )
        );
    }

    @PutMapping("/edit")
    public ResponseEntity<Response<UserResponse>> updateUser(@RequestBody UserEditRequest request) throws UserNotFoundException {
        return ResponseEntity.ok().body(
                new Response<>(
                        UserMappers.toUserResponse(editUserUseCase.editUser(UserMappers.toUser(request)))
                )
        );
    }

    @GetMapping("")
    public ResponseEntity<Response<UserResponse>> getUser(
            @RequestParam("email") String email,
            @RequestParam("phone") String phoneNumber
    ) throws UserNotFoundException {

        User userResponse = getUserByEmailOrPhoneUseCase.getUserByEmailOrPhoneNumber(email,phoneNumber);

        return ResponseEntity.ok().body(
                new Response<>(
                        UserMappers.toUserResponse(userResponse)
                )
        );

    }


}
