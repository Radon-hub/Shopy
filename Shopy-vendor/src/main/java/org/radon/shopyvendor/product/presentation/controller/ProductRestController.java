package org.radon.shopyvendor.product.presentation.controller;

import jakarta.websocket.server.PathParam;
import org.radon.shopyvendor.product.application.port.in.*;
import org.radon.shopyvendor.product.infrastructure.adapter.mapper.ProductMapper;
import org.radon.shopyvendor.product.presentation.dto.ProductRequest;
import org.radon.shopyvendor.product.presentation.dto.ProductResponse;
import org.radon.shopyvendor.shared.dto.PagedResponse;
import org.radon.shopyvendor.shared.dto.Response;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductRestController {


    private final AddNewProductUseCase addNewProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetProductsPaginationOfSpecificVendorUseCase getProductsPaginationOfSpecificVendorUseCase;

    public ProductRestController(AddNewProductUseCase addNewProductUseCase, DeleteProductUseCase deleteProductUseCase, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, GetProductsPaginationOfSpecificVendorUseCase getProductsPaginationOfSpecificVendorUseCase) {
        this.addNewProductUseCase = addNewProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.getProductsPaginationOfSpecificVendorUseCase = getProductsPaginationOfSpecificVendorUseCase;
    }


    @GetMapping("")
    public ResponseEntity<Response<ProductResponse>> getProduct(@RequestParam("productId") Long id) {
        return ResponseEntity.ok().body(
                new Response<>(
                        ProductMapper.toProductResponse(getProductUseCase.getProduct(id))
                )
        );
    }

    @GetMapping("all")
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(
            @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam("vendorId") Long vendorId
    ) {
        return ResponseEntity.ok().body(
                ProductMapper.toPagedResponse(
                        getProductsPaginationOfSpecificVendorUseCase
                                .getProductsPaginationOfSpecificVendorUseCase(pageable,vendorId)
                )
        );
    }

    @PostMapping("add")
    public ResponseEntity<Response<String>> addProduct(
            @RequestBody ProductRequest productRequest
    ) {

        addNewProductUseCase.addNewProduct(ProductMapper.toProductForAdd(productRequest));

        return ResponseEntity.ok().body(
                new Response<>(
                        "New product added successfully."
                )
        );
    }


    @DeleteMapping("remove/{id}")
    public ResponseEntity<Response<String>> removeProduct(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok().body(
                new Response<>(
                        deleteProductUseCase.deleteProduct(id)
                )
        );
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Response<ProductResponse>> updateProduct(
            @RequestBody ProductRequest productRequest,
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok().body(
                new Response<>(
                        ProductMapper.toProductResponse(updateProductUseCase.updateProduct(ProductMapper.toProductForUpdate(productRequest,id)))
                )
        );
    }





}
