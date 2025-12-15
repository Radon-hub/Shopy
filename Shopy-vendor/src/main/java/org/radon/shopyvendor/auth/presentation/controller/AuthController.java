package org.radon.shopyvendor.auth.presentation.controller;

import org.radon.shopyvendor.auth.application.port.in.LoadVendorByUserNameUseCase;
import org.radon.shopyvendor.auth.presentation.dto.AuthResponse;
import org.radon.shopyvendor.auth.presentation.dto.RefreshTokenRequest;
import org.radon.shopyvendor.auth.presentation.dto.VendorLoginRequest;
import org.radon.shopyvendor.shared.aop.security.JWTUtil;
import org.radon.shopyvendor.shared.dto.Response;
import org.radon.shopyvendor.auth.application.port.in.CreateVendorUseCase;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;
import org.radon.shopyvendor.vendor.presentation.dto.VendorCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/vendor")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CreateVendorUseCase createVendorUseCase;
    private final LoadVendorByUserNameUseCase loadVendorByUserNameUseCase;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, CreateVendorUseCase createVendorUseCase, LoadVendorByUserNameUseCase loadVendorByUserNameUseCase) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.createVendorUseCase = createVendorUseCase;
        this.loadVendorByUserNameUseCase = loadVendorByUserNameUseCase;
    }


    @PostMapping("signup")
    public ResponseEntity<Response<String>> createVendor(@RequestBody VendorCreateRequest vendorRequest) {
        return ResponseEntity.ok().body(
                new Response<>(
                        createVendorUseCase.createCall(VendorMappers.toVendor(vendorRequest))
                )
        );
    }

    @PostMapping("refresh-token")
    public ResponseEntity<Response<AuthResponse>> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest)  {

        String refreshUserName = jwtUtil.extractUsername(refreshTokenRequest.getRefreshToken());

        var user = loadVendorByUserNameUseCase.loadUserByUsername(refreshUserName);

        if(jwtUtil.isTokenValid(refreshTokenRequest.getRefreshToken(), user)){
            String newAccessToken = jwtUtil.generateAccessToken(user);
            String newRefreshToken = jwtUtil.generateRefreshToken(user);
            return ResponseEntity.ok().body(new Response<>(new AuthResponse(newAccessToken,newRefreshToken)));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @PostMapping("login")
    public ResponseEntity<Response<AuthResponse>> login(@RequestBody VendorLoginRequest vendorLoginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(vendorLoginRequest.getPhoneNumber(),vendorLoginRequest.getPassword())
        );

        UserDetails user = loadVendorByUserNameUseCase.loadUserByUsername(vendorLoginRequest.getPhoneNumber());

        String token = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        AuthResponse authResponse = new AuthResponse(token,refreshToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(authResponse));
    }



}
