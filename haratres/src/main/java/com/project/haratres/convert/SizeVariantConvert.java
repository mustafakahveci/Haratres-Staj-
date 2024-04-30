package com.project.haratres.convert;

import com.project.haratres.dto.VariantProductDto;
import com.project.haratres.enums.Size;
import com.project.haratres.model.ApparelSizeVariantProduct;
import com.project.haratres.model.ApparelStyleVariantProduct;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SizeVariantConvert {

    public ApparelSizeVariantProduct convert(VariantProductDto dto,
                                             ApparelStyleVariantProduct baseProduct) {

        ApparelSizeVariantProduct model = new ApparelSizeVariantProduct();

        model.setCode(dto.getCode());
        model.setPrice(dto.getPrice());
        model.setStock(dto.getStock());
        model.setBasedProduct(baseProduct);

        if (Objects.equals(dto.getSize(), "S")) {
            model.setSize(Size.S);
        } else if (Objects.equals(dto.getSize(), "M")) {
            model.setSize(Size.M);
        } else if (Objects.equals(dto.getSize(), "L")) {
            model.setSize(Size.L);
        }
        return model;
    }


}
