package delivery.management.system.service;

import delivery.management.system.model.dto.request.CategoryRequestDto;
import delivery.management.system.model.dto.response.CategoryResponseDto;
import delivery.management.system.model.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getByIds(List<Long> categoriesId);

    ResponseEntity<List<CategoryResponseDto>> findAll();

    ResponseEntity<CategoryResponseDto> findById(long id);

    ResponseEntity<Void> addCategory(CategoryRequestDto categoryRequestDto);

    ResponseEntity<Void> update(long id, CategoryRequestDto categoryRequestDto);

    ResponseEntity<Void> delete(long id);
}
