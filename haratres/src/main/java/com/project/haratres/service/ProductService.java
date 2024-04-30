package com.project.haratres.service;

import com.project.haratres.convert.SizeVariantConvert;
import com.project.haratres.convert.StyleVariantConvert;
import com.project.haratres.convert.VariantsInStyleVariantConvert;
import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.dto.ProductDto;
import com.project.haratres.dto.StyleProductDto;
import com.project.haratres.dto.VariantProductDto;
import com.project.haratres.exc.GenericException;
import com.project.haratres.model.ApparelSizeVariantProduct;
import com.project.haratres.model.ApparelStyleVariantProduct;
import com.project.haratres.model.Category;
import com.project.haratres.model.Product;
import com.project.haratres.repository.ApparelSizeVariantRepository;
import com.project.haratres.repository.ApparelStyleVariantRepository;
import com.project.haratres.repository.ProductRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Resource
    private final ProductRepository productRepository;

    @Resource
    private final ApparelStyleVariantRepository apparelStyleVariantRepository;

    @Resource
    private final ApparelSizeVariantRepository apparelSizeVariantRepository;

    @Resource
    private final StyleVariantConvert styleVariantConvert;

    @Resource
    private final SizeVariantConvert sizeVariantConvert;

    @Resource
    private final VariantsInStyleVariantConvert variantsInStyleVariantConvert;

    @Resource
    private final ModelMapper modelMapper;

    @Resource
    private final CategoryService categoryService;

    public List<StyleProductDto> getAllProductsWithParam(Optional<Long> categoryId) {
        List<ApparelStyleVariantProduct> products;

        if (categoryId.isPresent()) {
            Long id = categoryId.get();
            Category category = categoryService.findCategoryById(id);
            products = apparelStyleVariantRepository.findProductsByCategoryId(category.getId());
        } else {
            products = apparelStyleVariantRepository.findAll().stream()
                    .filter(product -> product.getClass().equals(ApparelStyleVariantProduct.class))
                    .collect(Collectors.toList());
        }
        return products.stream()
                .map(product -> {
                    StyleProductDto styleProductDto = modelMapper.map(product, StyleProductDto.class);
                    styleProductDto.setVariants(variantsInStyleVariantConvert.convertVariantsInStyleVariant(product));
                    return styleProductDto;
                }).collect(Collectors.toList());
    }

    public String createProduct(InboundProductDto request) {

        for (StyleProductDto styleProductDto : request.getProducts()) {
            //ürün var mı kontrol edilebilir update için

            ApparelStyleVariantProduct apparelStyleVariantProduct = styleVariantConvert.convert(styleProductDto);
            apparelStyleVariantRepository.save(apparelStyleVariantProduct);
            List<ApparelSizeVariantProduct> sizeVariantProductsList = new ArrayList<>();

            for (VariantProductDto variantProductDto : styleProductDto.getVariants()) {
                ApparelSizeVariantProduct apparelSizeVariantProduct = sizeVariantConvert.convert(variantProductDto, apparelStyleVariantProduct);
                apparelSizeVariantRepository.save(apparelSizeVariantProduct);
                sizeVariantProductsList.add(apparelSizeVariantProduct);
            }
            apparelStyleVariantProduct = styleVariantConvert.addVariants(apparelStyleVariantProduct, sizeVariantProductsList);
            apparelStyleVariantRepository.save(apparelStyleVariantProduct);
            sizeVariantProductsList.clear();
        }
        return "ürün yükleme başarılı";
    }

    public StyleProductDto getProductDetail(String code) {
        ApparelStyleVariantProduct product = findStyleVariantByCode(code);
        StyleProductDto dto = modelMapper.map(product, StyleProductDto.class);
        dto.setVariants(variantsInStyleVariantConvert.convertVariantsInStyleVariant(product));
        return dto;
    }

    public ApparelStyleVariantProduct findStyleVariantByCode(String code) {
        return apparelStyleVariantRepository.findById(code)
                .orElseThrow(() -> new GenericException("Product not found!", HttpStatus.NOT_FOUND));
    }
}
