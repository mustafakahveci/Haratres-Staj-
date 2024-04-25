package com.project.haratres.model;

import com.project.haratres.enums.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "SizeVariantProduct")
@Data
public class ApparelSizeVariantProduct extends ApparelStyleVariantProduct {

    private int stock;

    private Size size;

    private int price;

    @ManyToOne
    private ApparelStyleVariantProduct basedProduct;
}
