package com.project.haratres.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.DiscriminatorOptions;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorOptions(force = true)
public class Product {

    @Id
    @NotBlank(message = "Ürün kodu boş olamaz")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

}
