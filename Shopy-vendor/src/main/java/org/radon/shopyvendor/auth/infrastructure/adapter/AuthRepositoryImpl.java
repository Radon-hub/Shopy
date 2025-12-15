package org.radon.shopyvendor.auth.infrastructure.adapter;


import lombok.val;
import org.radon.shopyvendor.auth.application.port.out.AuthRepository;
import org.radon.shopyvendor.shared.aop.exceptionHandling.VendorExistsException;
import org.radon.shopyvendor.shared.aop.exceptionHandling.VendorNotFoundException;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;
import org.radon.shopyvendor.vendor.infrastructure.repository.VendorJpaRepository;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final VendorJpaRepository vendorJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthRepositoryImpl(VendorJpaRepository vendorJpaRepository, PasswordEncoder passwordEncoder) {
        this.vendorJpaRepository = vendorJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String createVendor(Vendor vendor) {

        vendor.isValidForRegister();

        Optional<VendorEntity> vendorEntity = vendorJpaRepository.findVendorEntitiesByPhoneNumber(vendor.getPhoneNumber());

        if(vendorEntity.isPresent()){
            throw new VendorExistsException();
        }

        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));

        vendorJpaRepository.save(VendorMappers.toVendorEntity(vendor));

        return "Vendor registered successfully!";
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        val vendor = getVendorWithPhoneNumber(username);

        if(vendor == null) throw new UsernameNotFoundException(username);

        return User.builder()
                .username(vendor.getPhoneNumber())
                .password(vendor.getPassword())
                .roles("USER")
                .disabled(false)
                .build();
    }

    @Override
    public Vendor getVendorWithPhoneNumber(String phoneNumber) {

        Optional<VendorEntity> vendorEntity = vendorJpaRepository.findVendorEntitiesByPhoneNumber(phoneNumber);

        if(vendorEntity.isEmpty()){
            throw new VendorNotFoundException(phoneNumber);
        }

        return VendorMappers.toVendor(vendorEntity.get());
    }
}
