package com.project.haratres.convert;

import com.project.haratres.dto.StyleProductDto;
import com.project.haratres.enums.Gender;
import com.project.haratres.model.ApparelSizeVariantProduct;
import com.project.haratres.model.ApparelStyleVariantProduct;
import com.project.haratres.model.Category;
import com.project.haratres.service.CategoryService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StyleVariantConvert {

    @Resource
    private final CategoryService categoryService;

    public ApparelStyleVariantProduct convert(StyleProductDto dto, ApparelStyleVariantProduct model) {

        Long categoryId = dto.getCategoryId();
        Category category = categoryService.findCategoryById(categoryId);
        if (category != null) {
            model.setCategory(category);
        }

        model.setName(dto.getName());
        model.setColour(dto.getColour());
        model.setDescription(dto.getDescription());
        model.setCode(dto.getCode());

        if (dto.getGender() == 'E' || dto.getGender() == 'e') {
            model.setGender(Gender.MALE);
        } else if (dto.getGender() == 'K' || dto.getGender() == 'k') {
            model.setGender(Gender.FEMALE);
        }
        return model;
    }

    public ApparelStyleVariantProduct addVariants(ApparelStyleVariantProduct model
            , List<ApparelSizeVariantProduct> variants) {
        model.setSizeVariantProducts(variants);
        return model;
    }
}
