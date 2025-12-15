package org.radon.shopy.user.infrastructure.adapter.mapper;

import org.radon.shopy.address.infrastructure.adapter.mapper.AddressMappers;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.user.domain.model.*;
import org.radon.shopy.user.infrastructure.repository.entity.UserEntity;
import org.radon.shopy.user.presentation.dto.UserEditRequest;
import org.radon.shopy.user.presentation.dto.UserResponse;

public class UserMappers {

    public static User toUser(UserEditRequest request) {
        return new User(
                null,
                new FullName(request.getFullName()),
                new Email(request.getEmail()),
                new PhoneNumber(request.getPhoneNumber()),
                null,
                null,
                new Age(request.getAge())
        );
    }

    public static PagedResponse<UserResponse> toPagedResponse(PagedResponse<User> pagedResponse) {
        return new PagedResponse<>(
                pagedResponse.getContent().stream().map(UserMappers::toUserResponse).toList(),
                pagedResponse.getPageNumber(),
                pagedResponse.getPageSize(),
                pagedResponse.getTotalPages(),
                pagedResponse.getTotalElements()
        );
    }

    public static UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getFullName().value(),
                user.getEmail().value(),
                user.getPhoneNumber().value(),
                AddressMappers.toAddressResponseListResponse(user.getAddressList()),
                user.getAge().value()
        );
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getFullName().value(),
                user.getEmail().value(),
                user.getPhoneNumber().value(),
                user.getPassword().value(),
                user.getAge().value()
        );
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                new FullName(userEntity.getFullName()),
                new Email(userEntity.getEmail()),
                new PhoneNumber(userEntity.getPhoneNumber()),
                new Password(userEntity.getPassword()),
                AddressMappers.toAddressResponseListModel(userEntity.getAddressEntities()),
                new Age(userEntity.getAge())
        );
    }

}
