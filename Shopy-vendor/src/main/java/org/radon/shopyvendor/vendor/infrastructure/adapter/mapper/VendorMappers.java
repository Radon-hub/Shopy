package org.radon.shopyvendor.vendor.infrastructure.adapter.mapper;

import org.radon.shopyvendor.product.infrastructure.adapter.mapper.ProductMapper;
import org.radon.shopyvendor.vendor.domain.model.Address;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.AddressEntity;
import org.radon.shopyvendor.vendor.infrastructure.repository.entity.VendorEntity;
import org.radon.shopyvendor.vendor.presentation.dto.*;

import java.util.Collections;

public class VendorMappers {


    public static VendorResponse toVendorResponse(Vendor vendor) {
        return new VendorResponse(
                vendor.getId(),
                vendor.getName(),
                vendor.getDescription(),
                vendor.getPhoneNumber(),
                vendor.getAddress()
        );
    }

    public static Vendor toVendor(VendorEditRequest vendorEditRequest,Long vendorId) {
        return new Vendor(
                vendorId,
                vendorEditRequest.getName(),
                vendorEditRequest.getDescription(),
                vendorEditRequest.getPhoneNumber(),
                null,
                vendorEditRequest.getAddress(),
                null
        );
    }

    public static Vendor toVendor(VendorCreateRequest vendorRequest) {
        return new Vendor(
                null,
                vendorRequest.getName(),
                vendorRequest.getDescription(),
                vendorRequest.getPhoneNumber(),
                vendorRequest.getPassword(),
                vendorRequest.getAddress(),
                null
        );
    }

    public static Vendor toVendor(Long id) {
        return new Vendor(
                id,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static Vendor toVendorWithProducts(VendorEntity vendorEntity) {
        return new Vendor(
                vendorEntity.getId(),
                vendorEntity.getName(),
                vendorEntity.getDescription(),
                vendorEntity.getPhoneNumber(),
                vendorEntity.getPassword(),
                VendorMappers.toAddress(vendorEntity.getAddress()),
                vendorEntity.getProductsList() == null ? Collections.emptyList() : vendorEntity.getProductsList().stream().map(ProductMapper::toProduct).toList()
        );
    }

    public static Vendor toVendor(VendorEntity vendorEntity) {
        return new Vendor(
                vendorEntity.getId(),
                vendorEntity.getName(),
                vendorEntity.getDescription(),
                vendorEntity.getPhoneNumber(),
                vendorEntity.getPassword(),
                VendorMappers.toAddress(vendorEntity.getAddress()),
                null
        );
    }

    public static Vendor toVendor(VendorDeleteRequest vendorDeleteRequest,Long vendorId) {
        return new Vendor(
                vendorId,
                null,
                null,
                vendorDeleteRequest.getPhoneNumber(),
                vendorDeleteRequest.getPassword(),
                null,
                null
        );
    }

    public static VendorEntity toVendorEntity(Vendor vendor) {
        return new VendorEntity(
                vendor.getName(),
                vendor.getDescription(),
                vendor.getPhoneNumber(),
                vendor.getPassword(),
                toAddressEntity(vendor.getAddress())
        );
    }

    public static Address toAddress(Address addressEntity) {
        return new Address(
                addressEntity.getTitle(),
                addressEntity.getProvince(),
                addressEntity.getCity(),
                addressEntity.getArea(),
                addressEntity.getStreet(),
                addressEntity.getNumber()
        );
    }

    public static AddressEntity toAddressEntity(Address address) {
        return new AddressEntity(
                address.getTitle(),
                address.getProvince(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getNumber()
        );
    }

    public static Address toAddress(AddressEntity addressEntity) {
        return new Address(
                addressEntity.getTitle(),
                addressEntity.getProvince(),
                addressEntity.getCity(),
                addressEntity.getArea(),
                addressEntity.getStreet(),
                addressEntity.getNumber()
        );
    }



}
