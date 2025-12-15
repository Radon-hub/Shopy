package org.radon.shopy.address.infrastructure.adapter.mapper;

import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.infrastructure.repository.entity.AddressEntity;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.address.presentation.dto.AddressResponse;
import org.radon.shopy.user.domain.model.PhoneNumber;
import org.radon.shopy.user.domain.model.User;
import org.radon.shopy.user.infrastructure.adapter.mapper.UserMappers;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressMappers {


    public static Address toAddress(AddAddressRequest address) {
        return new Address(
                address.title(),
                address.province(),
                address.city(),
                address.area(),
                address.street(),
                address.extraDetails(),
                null
        );
    }

    public static AddressResponse toAddressResponse(Address address) {
        return new AddressResponse(
                address.getTitle(),
                address.getProvince(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getExtraDetails()
        );
    }

    public static List<AddressResponse> toAddressResponseListResponse(List<Address> addresses){
        return addresses.stream().map(AddressMappers::toAddressResponse).collect(Collectors.toList());
    }

    public static List<Address> toAddressResponseList(List<AddressEntity> addresses){
        return addresses.stream().map(AddressMappers::toAddress).collect(Collectors.toList());
    }

    public static List<Address> toAddressResponseListModel(List<AddressEntity> addresses){
        if(addresses == null){
            return new ArrayList<>();
        }else{
            return addresses.stream().map(AddressMappers::toAddress).collect(Collectors.toList());
        }
    }

    public static AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getTitle(),
                address.getProvince(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getExtraDetails(),
                UserMappers.toEntity(address.getUser())
        );
    }

    public static AddressResponse toAddressResponse(AddressEntity address) {
        return new AddressResponse(
                address.getTitle(),
                address.getProvince(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getExtraDetails()
        );
    }

    public static Address toAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getTitle(),
                addressEntity.getProvince(),
                addressEntity.getCity(),
                addressEntity.getArea(),
                addressEntity.getStreet(),
                addressEntity.getExtraDetails(),
                addressEntity.getUserEntity() != null ? toUserJustPhone(addressEntity.getUserEntity()) : null
        );
    }

    public static User toUserJustPhone(UserEntity userEntity) {
        return new User(
                null,null,null,new PhoneNumber(userEntity.getPhoneNumber()),null,null,null
        );
    }


}
