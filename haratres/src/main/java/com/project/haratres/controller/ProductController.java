package com.project.haratres.controller;

import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.model.Product;
import com.project.haratres.service.ProductService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Resource
    private final ProductService productService;


    @GetMapping
    //@PreAuthorize("hasAuthority('CUSTOMER')")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public String createProduct(@RequestBody InboundProductDto request){
        return productService.createProduct(request);
    }

}
