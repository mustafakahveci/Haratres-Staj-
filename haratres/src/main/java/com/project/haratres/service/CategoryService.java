package com.project.haratres.service;

import com.project.haratres.dto.CategoryDto;
import com.project.haratres.exc.GenericException;
import com.project.haratres.model.Category;
import com.project.haratres.repository.CategoryRepository;
import com.project.haratres.request.CategoryCreateRequest;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private ModelMapper modelMapper;

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new GenericException("Category not found!", HttpStatus.NOT_FOUND));
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryCreateRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDto.class);
    }
}
