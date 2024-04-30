package com.project.haratres.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String name;
    private String code;
    List<VariantProductDto> variants;
}
