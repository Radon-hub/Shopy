package org.radon.shopy.address.presentation.controller;


import org.radon.shopy.address.application.port.in.AddAddressForUserUseCase;
import org.radon.shopy.address.application.port.in.EditAddressUseCase;
import org.radon.shopy.address.application.port.in.GetAllAddressesUseCase;
import org.radon.shopy.address.domain.model.Address;
import org.radon.shopy.address.infrastructure.adapter.mapper.AddressMappers;
import org.radon.shopy.shared.aop.exceptionHandling.model.UserNotFoundException;
import org.radon.shopy.shared.dto.Response;
import org.radon.shopy.address.presentation.dto.AddAddressRequest;
import org.radon.shopy.address.presentation.dto.AddressResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/address")
public class AddressController {

    private final AddAddressForUserUseCase addAddressForUserUseCase;
    private final GetAllAddressesUseCase getAllAddressesUseCase;
    private final EditAddressUseCase editAddressUseCase;

    public AddressController(AddAddressForUserUseCase addAddressForUserUseCase, GetAllAddressesUseCase getAllAddressesUseCase, EditAddressUseCase editAddressUseCase) {
        this.addAddressForUserUseCase = addAddressForUserUseCase;
        this.getAllAddressesUseCase = getAllAddressesUseCase;
        this.editAddressUseCase = editAddressUseCase;
    }

    @GetMapping("all")
    public ResponseEntity<Response<List<AddressResponse>>> get() {
        return ResponseEntity.ok().body(
                new Response<>(
                        getAllAddressesUseCase.returnAllAddresses().stream().map(AddressMappers::toAddressResponse).toList()
                )
        );
    }

    @PostMapping("add")
    public ResponseEntity<Response<String>> addAddress(
            @RequestBody AddAddressRequest addAddressRequest
    ) throws UserNotFoundException {

        Address result = addAddressForUserUseCase.addAddressForUser(
                addAddressRequest
        );

        if(result == null) {
            return ResponseEntity.ok().body(
                    new Response<>(
                        "Add address failed!"
                    )
            );
        }else{
            return ResponseEntity.badRequest().body(
                    new Response<>(
                        "Address added successfully!"
                    )
            );
        }

    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Response<AddressResponse>> editAddress(
            @PathVariable("id") Long id,
            @RequestBody AddAddressRequest addAddressRequest
    ){
        return ResponseEntity.ok().body(
                new Response<>(
                        AddressMappers.toAddressResponse(
                                editAddressUseCase.editAddress(id,addAddressRequest)
                        )
                )
        );
    }

}
