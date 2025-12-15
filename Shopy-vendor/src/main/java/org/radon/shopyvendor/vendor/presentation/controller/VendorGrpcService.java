package org.radon.shopyvendor.vendor.presentation.controller;

import com.radon.grpc.VendorRequest;
import com.radon.grpc.VendorResponse;
import com.radon.grpc.VendorsGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.transaction.Transactional;
import org.radon.shopyvendor.vendor.application.port.in.GetVendorUseCase;
import org.radon.shopyvendor.vendor.domain.model.Address;
import org.radon.shopyvendor.vendor.domain.model.Vendor;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
public class VendorGrpcService extends VendorsGrpc.VendorsImplBase {


    private final GetVendorUseCase getVendorUseCase;

    public VendorGrpcService(GetVendorUseCase getVendorUseCase) {
        this.getVendorUseCase = getVendorUseCase;
    }

    @Transactional
    @Override
    public void getVendorById(VendorRequest request, StreamObserver<VendorResponse> responseObserver) {

        Vendor vendor = getVendorUseCase.getVendorCall(request.getId());

        responseObserver.onNext(toVendorResponse(vendor));
        responseObserver.onCompleted();

    }

    public VendorResponse toVendorResponse(Vendor vendor) {
        Address address = vendor.getAddress();
        return VendorResponse.newBuilder()
                .setId(vendor.getId())
                .setName(vendor.getName())
                .setDescription(vendor.getDescription())
                .setPhoneNumber(String.valueOf(vendor.getPhoneNumber()))
                .setAddress(com.radon.grpc.AddressResponse.newBuilder()
                        .setArea(address.getArea())
                        .setCity(address.getCity())
                        .setNumber(address.getNumber())
                        .setTitle(address.getTitle())
                        .setStreet(address.getStreet())
                        .setProvince(address.getProvince())
                        .build())
                .build();
    }
}