package com.project.haratres.repository;

import com.project.haratres.model.ApparelStyleVariantProduct;
import com.project.haratres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApparelStyleVariantRepository extends JpaRepository<ApparelStyleVariantProduct,String> {
    List<Product> findProductsByCategoryId(Long categoryId);
}
