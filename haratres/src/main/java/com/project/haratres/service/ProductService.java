package com.project.haratres.service;

import com.project.haratres.dto.InboundProductDto;
import com.project.haratres.dto.StyleProductDto;
import com.project.haratres.dto.VariantProductDto;
import com.project.haratres.enums.Gender;
import com.project.haratres.enums.Size;
import com.project.haratres.model.ApparelSizeVariantProduct;
import com.project.haratres.model.ApparelStyleVariantProduct;
import com.project.haratres.model.Category;
import com.project.haratres.model.Product;
import com.project.haratres.repository.ApparelSizeVariantRepository;
import com.project.haratres.repository.ApparelStyleVariantRepository;
import com.project.haratres.repository.ProductRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final CategoryService categoryService;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public String createProduct(InboundProductDto request) {

        for (StyleProductDto styleProductDto : request.getProducts()) {
            ApparelStyleVariantProduct apparelStyleVariantProduct = new ApparelStyleVariantProduct();

            Long categoryId = styleProductDto.getCategoryId();
            Category category = categoryService.findCategoryById(categoryId);

            if (category != null) {
                apparelStyleVariantProduct.setCategory(category);
            }

            apparelStyleVariantProduct.setName(styleProductDto.getName());
            apparelStyleVariantProduct.setColour(styleProductDto.getColour());
            apparelStyleVariantProduct.setDescription(styleProductDto.getDescription());
            apparelStyleVariantProduct.setCode(styleProductDto.getCode());

            if (styleProductDto.getGender() == 'E' || styleProductDto.getGender() == 'e') {
                apparelStyleVariantProduct.setGender(Gender.MALE);
            } else if (styleProductDto.getGender() == 'K' || styleProductDto.getGender() == 'k') {
                apparelStyleVariantProduct.setGender(Gender.FEMALE);
            }

            for (VariantProductDto variantProductDto : styleProductDto.getVariants()) {
                ApparelSizeVariantProduct apparelSizeVariantProduct = new ApparelSizeVariantProduct();

                if (Objects.equals(variantProductDto.getSize(), "S")) apparelSizeVariantProduct.setSize(Size.S);
                else if (Objects.equals(variantProductDto.getSize(), "M")) {
                    apparelSizeVariantProduct.setSize(Size.M);
                } else if (Objects.equals(variantProductDto.getSize(), "L")) {
                    apparelSizeVariantProduct.setSize(Size.L);
                }

                apparelSizeVariantProduct.setBasedProduct(apparelStyleVariantProduct);
                apparelSizeVariantProduct.setPrice(variantProductDto.getPrice());
                apparelSizeVariantProduct.setStock(variantProductDto.getStock());
                apparelSizeVariantProduct.setCode(variantProductDto.getCode());

                apparelSizeVariantRepository.save(apparelSizeVariantProduct);
                if (Objects.isNull(apparelStyleVariantProduct.getSizeVariantProducts())) {
                    List<ApparelSizeVariantProduct> sizeVariantProducts = new ArrayList<>();
                    sizeVariantProducts.add(apparelSizeVariantProduct);
                    apparelStyleVariantProduct.setSizeVariantProducts(sizeVariantProducts);
                } else {
                    apparelStyleVariantProduct.getSizeVariantProducts().add(apparelSizeVariantProduct);
                }
            }
            apparelStyleVariantRepository.save(apparelStyleVariantProduct);
        }
        return "ürün yükleme başarılı";
    }


}
