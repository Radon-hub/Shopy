package org.radon.shopyvendor.vendor.presentation.controller;


import org.radon.shopyvendor.shared.dto.Response;
import org.radon.shopyvendor.vendor.application.port.in.*;
import org.radon.shopyvendor.vendor.infrastructure.adapter.mapper.VendorMappers;
import org.radon.shopyvendor.vendor.presentation.dto.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorRestController {

    private final DeleteVendorUseCase deleteVendorUseCase;
    private final EditVendorUseCase editVendorUseCase;
    private final GetVendorUseCase getVendorUseCase;

    public VendorRestController(DeleteVendorUseCase deleteVendorUseCase, EditVendorUseCase editVendorUseCase, GetVendorUseCase getVendorUseCase) {
        this.deleteVendorUseCase = deleteVendorUseCase;
        this.editVendorUseCase = editVendorUseCase;
        this.getVendorUseCase = getVendorUseCase;
    }


    @GetMapping("")
    public ResponseEntity<Response<VendorResponse>> getVendor(@RequestParam("vendorId") Long id) {
        return ResponseEntity.ok().body(
                new Response<>(
                        VendorMappers.toVendorResponse(getVendorUseCase.getVendorCall(id))
                )
        );
    }


    @PutMapping("edit/{id}")
    public ResponseEntity<Response<VendorResponse>> editVendor(
            @RequestBody VendorEditRequest vendorEditRequest,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok().body(
                new Response<>(
                    VendorMappers.toVendorResponse(editVendorUseCase.editCall(VendorMappers.toVendor(vendorEditRequest,id)))
                )
        );
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response<String>> deleteVendor(
            @PathVariable("id")  Long id,
            @RequestBody VendorDeleteRequest vendorDeleteRequest
    ){
        return  ResponseEntity.ok().body(
                new Response<>(
                        deleteVendorUseCase.deleteCall(VendorMappers.toVendor(vendorDeleteRequest,id))
                )
        );
    }

}
