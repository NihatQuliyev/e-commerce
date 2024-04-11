package delivery.management.system.controller;

import delivery.management.system.model.dto.request.CategoryRequestDto;
import delivery.management.system.model.dto.response.CategoryResponseDto;
import delivery.management.system.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("category/")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<CategoryResponseDto>> categories() {
        return categoryService.findAll();
    }


    @GetMapping({"{category-id}"})
    ResponseEntity<CategoryResponseDto> findById(@PathVariable(name = "category-id") long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    ResponseEntity<Void> addCategories(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return categoryService.addCategory(categoryRequestDto);
    }


    @PutMapping("{category-id}")
    ResponseEntity<Void> update(@PathVariable(name = "category-id") long id,
                                @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        return categoryService.update(id, categoryRequestDto);
    }


    @DeleteMapping("{category-id}")
    ResponseEntity<Void> delete(@PathVariable(name = "category-id") long id) {
        return categoryService.delete(id);
    }
}
