package org.radon.shopyvendor.auth.application.service;

import org.radon.shopyvendor.auth.application.port.in.CreateVendorUseCase;
import org.radon.shopyvendor.auth.application.port.in.GetCurrentVendorUseCase;
import org.radon.shopyvendor.auth.application.port.in.GetVendorByPhoneNumber;
import org.radon.shopyvendor.auth.application.port.in.LoadVendorByUserNameUseCase;
import org.radon.shopyvendor.auth.application.port.out.AuthRepository;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements CreateVendorUseCase, LoadVendorByUserNameUseCase, GetVendorByPhoneNumber, GetCurrentVendorUseCase {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    @Override
    public String createCall(Vendor vendor) {
        return authRepository.createVendor(vendor);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return authRepository.loadUserByUsername(username);
    }

    @Override
    public Vendor getByPhoneCall(String phoneNumber) {
        return authRepository.getVendorWithPhoneNumber(phoneNumber);
    }

    @Override
    public String getCurrentVendor() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
