package delivery.management.system.controller;

import delivery.management.system.model.dto.request.ProductRequestDto;
import delivery.management.system.model.dto.request.ProductUpdateRequestDto;
import delivery.management.system.model.dto.response.ProductResponseDto;
import delivery.management.system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("product/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    ResponseEntity<List<ProductResponseDto>> products() {
        return productService.findAllProduct();
    }

    @PostMapping
    ResponseEntity<Void> addProduct(@RequestBody @Valid ProductRequestDto productRequest) {
        return productService.addProduct(productRequest);
    }

    @PostMapping("uploading/{product-id}")
    ResponseEntity<Void> addProductImage(@RequestPart("multipart-file") MultipartFile multipartFile,
                                         @PathVariable(name = "product-id") long productId) {
        return productService.addProductImage(multipartFile,productId);

    }
    @GetMapping("{product-id}")
    ResponseEntity<ProductResponseDto> getById(@PathVariable(name = "product-id") long id) {
        return productService.findById(id);
    }

    @PutMapping("{product-id}")
    ResponseEntity<Void> update(@RequestBody @Valid ProductUpdateRequestDto productRequestDto,
                                @PathVariable(name = "product-id") long id) {
        return productService.update(productRequestDto, id);
    }

    @DeleteMapping("{product-id}")
    ResponseEntity<Void> delete(@PathVariable(name = "product-id") long id) {
        return productService.delete(id);
    }
}
