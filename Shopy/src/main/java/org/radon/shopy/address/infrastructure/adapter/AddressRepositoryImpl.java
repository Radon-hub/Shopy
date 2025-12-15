package org.radon.shopy.address.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.val;
import org.radon.shopy.address.application.port.out.AddressRepository;
import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.infrastructure.adapter.mapper.AddressMappers;
import org.radon.shopy.address.infrastructure.repository.AddressJpaRepository;
import org.radon.shopy.address.infrastructure.repository.entity.AddressEntity;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.address.presentation.dto.AddressResponse;
import org.radon.shopy.auth.application.port.in.GetCurrentUserUseCase;
import org.radon.shopy.auth.application.service.AuthService;
import org.radon.shopy.shared.aop.exceptionHandling.model.AccessDeniedException;
import org.radon.shopy.shared.aop.exceptionHandling.model.AddressNotFoundException;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.user.infrastructure.repository.UserJpaRepository;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;


    public AddressRepositoryImpl(AddressJpaRepository addressJpaRepository, UserJpaRepository userJpaRepository, GetCurrentUserUseCase getCurrentUserUseCase) {
        this.addressJpaRepository = addressJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
    }


    @Override
    public List<Address> returnAllAddresses() {
        val list = addressJpaRepository.findAll();
        return AddressMappers.toAddressResponseList(list);
    }

    @Transactional
    @Override
    public Address editAddress(Long id, AddAddressRequest addAddressRequest) {

        AddressEntity address = addressJpaRepository.findById(id).orElseThrow(AddressNotFoundException::new);


        String currentUser = getCurrentUserUseCase.getCurrentUser();


        if (!Objects.equals(address.getUserEntity().getPhoneNumber(), currentUser)) {
            throw new AccessDeniedException("Access denied: This address does not belong to the user");
        }

        address.setTitle(addAddressRequest.title());
        address.setCity(addAddressRequest.city());
        address.setArea(addAddressRequest.area());
        address.setStreet(addAddressRequest.street());
        address.setProvince(addAddressRequest.province());
        address.setExtraDetails(addAddressRequest.extraDetails());

        return new Address(
                address.getTitle(),
                address.getProvince(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getExtraDetails(),
                null
        );
    }

    @Override
    public Address createAddressForUser(AddAddressRequest addAddressRequest) {

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
            throw new AccessDeniedException("Access denied: This address does not belong to the user");
        }

        AddressEntity savedAddress = addressJpaRepository.save(new AddressEntity(
                addAddressRequest.title(),
                addAddressRequest.province(),
                addAddressRequest.city(),
                addAddressRequest.area(),
                addAddressRequest.street(),
                addAddressRequest.extraDetails(),
                userEntity
        ));

        return AddressMappers.toAddress(savedAddress);
    }

}
