package org.radon.shopy.integration.vendor.presentation.controller;

import org.radon.shopy.integration.vendor.application.port.in.GetProductPagedUseCase;
import org.radon.shopy.integration.vendor.application.port.in.GetProductUseCase;
import org.radon.shopy.integration.vendor.application.port.in.GetVendorUseCase;
import org.radon.shopy.integration.vendor.infrastructure.adapter.mapper.GRPCMapper;
import org.radon.shopy.integration.vendor.presentation.dto.ProductResponseDto;
import org.radon.shopy.integration.vendor.presentation.dto.VendorResponseDto;
import org.radon.shopy.shared.dto.PagedResponse;
import org.radon.shopy.shared.dto.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    private final GetVendorUseCase getVendorUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetProductPagedUseCase getProductPagedUseCase;

    public VendorController(GetVendorUseCase getVendorUseCase, GetProductUseCase getProductUseCase, GetProductPagedUseCase getProductPagedUseCase) {
        this.getVendorUseCase = getVendorUseCase;
        this.getProductUseCase = getProductUseCase;
        this.getProductPagedUseCase = getProductPagedUseCase;
    }


    @GetMapping("{id}")
    public ResponseEntity<Response<VendorResponseDto>> getVendor(@PathVariable Long id) {
        return new ResponseEntity<>(new Response<VendorResponseDto>(GRPCMapper.toVendorResponseDto(getVendorUseCase.getVendor(id))), HttpStatus.OK);
    }

    @GetMapping("products")
    public ResponseEntity<Response<ProductResponseDto>> getProduct(
            @RequestParam("productId") Long id
    ) {
        return new ResponseEntity<>(new Response<ProductResponseDto>(GRPCMapper.toProductResponseDto(getProductUseCase.getProduct(id))), HttpStatus.OK);
    }

    @GetMapping("products/{id}/all")
    public ResponseEntity<Response<PagedResponse<ProductResponseDto>>> getProductPaged(
            @PathVariable Long id,
            @PageableDefault(size = 10,page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(new Response<PagedResponse<ProductResponseDto>>(GRPCMapper.toPagedProductsDto(getProductPagedUseCase.getProductsPaged(pageable,id))), HttpStatus.OK);
    }
}
