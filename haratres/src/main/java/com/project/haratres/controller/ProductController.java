package com.project.haratres.controller;

import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.dto.StyleProductDto;
import com.project.haratres.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
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
    public List<StyleProductDto> getAllProductsWithParam(@RequestParam Optional<Long> categoryId) {
        return productService.getAllProductsWithParam(categoryId);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct(@Valid @RequestBody InboundProductDto request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{code}")
    public StyleProductDto getProductDetail(@PathVariable String code){
        return productService.getProductDetail(code);
    }

}
