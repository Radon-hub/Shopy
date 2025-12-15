package org.radon.shopy.auth.presentation.controller;


import lombok.val;
import org.radon.shopy.auth.application.port.in.CreateUserUseCase;
import org.radon.shopy.auth.application.port.in.LoadUserByUserNameUseCase;
import org.radon.shopy.auth.infrastructure.adapter.mapper.AuthMappers;
import org.radon.shopy.shared.aop.exceptionHandling.model.IllegalArgumentException;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserExistException;
import org.radon.shopy.shared.aop.security.JWTUtil;
import org.radon.shopy.shared.dto.Response;
import org.radon.shopy.auth.presentation.dto.RefreshTokenRequest;
import org.radon.shopy.user.presentation.dto.UserCreateRequest;
import org.radon.shopy.auth.presentation.dto.UserLoginRequest;
import org.radon.shopy.auth.presentation.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CreateUserUseCase createUserUseCase;
    private final LoadUserByUserNameUseCase loadUserByUserNameUseCase;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CreateUserUseCase createUserUseCase, LoadUserByUserNameUseCase loadUserByUserNameUseCase) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.createUserUseCase = createUserUseCase;
        this.loadUserByUserNameUseCase = loadUserByUserNameUseCase;
    }


    @PostMapping("signup")
    public ResponseEntity<Response<String>> createUser(@RequestBody UserCreateRequest user) {
        val result = createUserUseCase.createUser(AuthMappers.toUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>("User created successfully!"));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<Response<AuthResponse>> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest)  {

        String refreshUserName = jwtUtil.extractUsername(refreshTokenRequest.getRefreshToken());

        var user = loadUserByUserNameUseCase.loadUserByUsername(refreshUserName);

        if(jwtUtil.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            String newAccessToken = jwtUtil.generateToken(user);
            String newRefreshToken = jwtUtil.generateRefreshToken(user);
            return ResponseEntity.ok().body(new Response<>(new AuthResponse(newAccessToken,newRefreshToken)));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @PostMapping("login")
    public ResponseEntity<Response<AuthResponse>> login(@RequestBody UserLoginRequest userLoginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequest.phoneNumber(),userLoginRequest.password())
        );

        String identifier = userLoginRequest.email().contains("@") ? userLoginRequest.email() : userLoginRequest.phoneNumber();
        UserDetails user = loadUserByUserNameUseCase.loadUserByUsername(identifier);

        String token = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        AuthResponse authResponse = new AuthResponse(token,refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(authResponse));
    }

}
