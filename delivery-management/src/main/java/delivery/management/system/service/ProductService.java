package delivery.management.system.service;


import delivery.management.system.model.dto.request.ProductRequestDto;
import delivery.management.system.model.dto.request.ProductUpdateRequestDto;
import delivery.management.system.model.dto.response.ProductResponseDto;
import delivery.management.system.model.entity.CartItem;
import delivery.management.system.model.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    Product getById(long productId);

    void decreaseProductCount(List<CartItem> cartItems);

    ResponseEntity<List<ProductResponseDto>> findAllProduct();

    ResponseEntity<Void> addProduct(ProductRequestDto productRequest);

    ResponseEntity<Void> addProductImage(MultipartFile multipartFile, long productId);

    ResponseEntity<ProductResponseDto> findById(long id);

    ResponseEntity<Void> update(ProductUpdateRequestDto productRequestDto, long id);

    ResponseEntity<Void> delete(long id);
}
