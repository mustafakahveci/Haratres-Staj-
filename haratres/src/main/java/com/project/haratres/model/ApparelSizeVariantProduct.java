package com.project.haratres.model;

import com.project.haratres.enums.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "SizeVariantProduct")
@Getter
@Setter
public class ApparelSizeVariantProduct extends ApparelStyleVariantProduct {

    @NotNull(message = "Stok miktarı belirtilmelidir")
    @Min(value = 0, message = "Stok miktarı en az 0 olmalıdır")
    private int stock;

    @NotNull(message = "Bir beden belirtilmelidir")
    private Size size;

    @NotNull(message = "Fiyat belirtilmelidir")
    @Min(value = 0, message = "Fiyat en az 0 olmalıdır")
    private int price;

    @ManyToOne
    @NotNull(message = "Base ürün belirtilmelidir")
    private ApparelStyleVariantProduct basedProduct;
}
