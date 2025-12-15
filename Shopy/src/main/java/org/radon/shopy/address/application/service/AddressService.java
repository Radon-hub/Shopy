package org.radon.shopy.address.application.service;

import org.radon.shopy.address.application.port.in.AddAddressForUserUseCase;
import org.radon.shopy.address.application.port.in.EditAddressUseCase;
import org.radon.shopy.address.application.port.in.GetAllAddressesUseCase;
import org.radon.shopy.address.application.port.out.AddressRepository;
import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.address.presentation.dto.AddressResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddAddressForUserUseCase,GetAllAddressesUseCase, EditAddressUseCase {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    @Caching(
            put = { @CachePut(value = "addresses") },
            evict = { @CacheEvict(value = "user",key = "#result.user.phoneNumber") }
    )
    public Address addAddressForUser(AddAddressRequest addAddressRequest) {
        return addressRepository.createAddressForUser(addAddressRequest);
    }

    @Override
    @Cacheable(value = "addresses")
    public List<Address> returnAllAddresses() {
        return addressRepository.returnAllAddresses();
    }


    @Override
    @CachePut(value = "addresses")
    public Address editAddress(Long id, AddAddressRequest addAddressRequest) {
        return addressRepository.editAddress(id,addAddressRequest);
    }
}
