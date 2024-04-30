package com.project.haratres.model;

import com.project.haratres.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "StyleVariantProduct")
@Getter
@Setter
public class ApparelStyleVariantProduct extends Product {

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    private String colour;

    private Gender gender;

    @Size(min = 4, max = 40, message = "Ürün adı 4 ile 40 karakter arasında olmalıdır")
    private String name;

    @Size(min = 10, max = 200, message = "Açıklama 10 ile 200 karakter arasında olmalıdır")
    private String description;

    @OneToMany
    private List<ApparelSizeVariantProduct> sizeVariantProducts;

}
