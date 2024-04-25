package com.project.haratres.model;

import com.project.haratres.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "StyleVariantProduct")
@Data
public class ApparelStyleVariantProduct extends Product{

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private String colour;

    private Gender gender;

    private String name;
    private String description;

    @OneToMany
    private List<ApparelSizeVariantProduct> sizeVariantProducts ;

}
