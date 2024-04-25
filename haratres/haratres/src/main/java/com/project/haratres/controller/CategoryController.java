package com.project.haratres.controller;

import com.project.haratres.dto.CategoryDto;
import com.project.haratres.request.CategoryCreateRequest;
import com.project.haratres.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public CategoryDto creatCategory(@RequestBody CategoryCreateRequest request){
        return categoryService.createCategory(request);
    }
}
