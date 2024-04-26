package com.project.haratres.controller;

import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.dto.ProductDto;
import com.project.haratres.service.ProductService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Resource
    private final ProductService productService;

    @GetMapping
    //@PreAuthorize("hasAuthority('CUSTOMER')")
    public List<ProductDto> getAllProductsWithParam(@RequestParam Optional<Long> categoryId) {
        return productService.getAllProductsWithParam(categoryId);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct(@RequestBody InboundProductDto request) {
        return productService.createProduct(request);
    }

}
