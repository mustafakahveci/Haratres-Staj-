package com.project.haratres.convert;

import com.project.haratres.dto.VariantProductDto;
import com.project.haratres.model.ApparelStyleVariantProduct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VariantsInStyleVariantConvert {

    @Resource
    private final ModelMapper modelMapper;

    public List<VariantProductDto> convertVariantsInStyleVariant(ApparelStyleVariantProduct products) {
        return products.getSizeVariantProducts().stream()
                .map(x -> modelMapper.map(x, VariantProductDto.class))
                .collect(Collectors.toList());
    }

}
