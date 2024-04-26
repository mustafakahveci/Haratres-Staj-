package com.project.haratres.service;

import com.project.haratres.convert.SizeVariantConvert;
import com.project.haratres.convert.StyleVariantConvert;
import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.dto.ProductDto;
import com.project.haratres.dto.StyleProductDto;
import com.project.haratres.dto.VariantProductDto;
import com.project.haratres.model.ApparelSizeVariantProduct;
import com.project.haratres.model.ApparelStyleVariantProduct;
import com.project.haratres.model.Category;
import com.project.haratres.repository.ApparelSizeVariantRepository;
import com.project.haratres.repository.ApparelStyleVariantRepository;
import com.project.haratres.repository.ProductRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Resource
    private final CategoryService categoryService;

    public List<ProductDto> getAllProductsWithParam(Optional<Long> categoryId) {
        if (categoryId.isPresent()) {
            Long id = categoryId.get();
            Category inDB = categoryService.findCategoryById(id);
            return apparelStyleVariantRepository.findProductsByCategoryId(inDB.getId()).stream()
                    .map(x -> modelMapper.map(x, ProductDto.class))
                    .collect(Collectors.toList());

        } else {
            return productRepository.findAll().stream()
                    .map(book -> modelMapper.map(book, ProductDto.class))
                    .collect(Collectors.toList());
        }
    }

    public String createProduct(InboundProductDto request) {

        for (StyleProductDto styleProductDto : request.getProducts()) {
            ApparelStyleVariantProduct apparelStyleVariantProduct = new ApparelStyleVariantProduct();
            apparelStyleVariantProduct = styleVariantConvert.convert(styleProductDto, apparelStyleVariantProduct);
            apparelStyleVariantRepository.save(apparelStyleVariantProduct);

            List<ApparelSizeVariantProduct> sizeVariantProductsList = new ArrayList<>();

            for (VariantProductDto variantProductDto : styleProductDto.getVariants()) {

                ApparelSizeVariantProduct apparelSizeVariantProduct = new ApparelSizeVariantProduct();
                apparelSizeVariantProduct = sizeVariantConvert.convert(variantProductDto,
                        apparelSizeVariantProduct, apparelStyleVariantProduct);
                apparelSizeVariantRepository.save(apparelSizeVariantProduct);

                sizeVariantProductsList.add(apparelSizeVariantProduct);
            }
            apparelStyleVariantProduct = styleVariantConvert.addVariants(apparelStyleVariantProduct, sizeVariantProductsList);
            apparelStyleVariantRepository.save(apparelStyleVariantProduct);
            sizeVariantProductsList.clear();
        }
        return "ürün yükleme başarılı";
    }

}
