package delivery.management.system.service.impl;

import common.exception.model.exception.ApplicationException;
import common.exception.model.service.ExceptionService;
import delivery.management.system.mapper.ProductMapper;
import delivery.management.system.model.dto.request.ProductRequestDto;
import delivery.management.system.model.dto.request.ProductUpdateRequestDto;
import delivery.management.system.model.dto.response.ProductResponseDto;
import delivery.management.system.model.entity.CartItem;
import delivery.management.system.model.entity.Category;
import delivery.management.system.model.entity.Image;
import delivery.management.system.model.entity.Product;
import delivery.management.system.repository.ProductRepository;
import delivery.management.system.service.CategoryService;
import delivery.management.system.service.FileService;
import delivery.management.system.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ExceptionService exceptionService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final FileService fileService;

    @Override
    public Product getById(long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ApplicationException(exceptionService.exceptionResponse("exception.product.not.found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public void decreaseProductCount(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            productRepository.decreaseProductCount(cartItem.getCount(), cartItem.getProduct().getId());
        }
    }

    @Override
    public ResponseEntity<List<ProductResponseDto>> findAllProduct() {

        List<Product> products = productRepository.findAllByStatusTrue();
        List<ProductResponseDto> productsResponse = new LinkedList<>();

        for (Product product : products) {
            ProductResponseDto productResponse = productMapper.map(product);
            productResponse.setImagesPath(fileService.findByName(product.getImages()));
            productsResponse.add(productResponse);

        }
        return ResponseEntity.ok(productsResponse);

    }

    @Override
    public ResponseEntity<Void> addProduct(ProductRequestDto productRequest) {
        List<Category> categories = categoryService.getByIds(productRequest.getCategoriesId());
        Product product = productMapper.map(productRequest);
        if (categories != null) {
            product.setCategories(categories);
        }
        productRepository.save(product);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> addProductImage(MultipartFile multipartFile, long productId) {
        Product product = getById(productId);
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .product(product)
                .build();
        product.setImages(List.of(image));

        fileService.upload(multipartFile);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponseDto> findById(long id) {
        Product product =  productRepository.findByIdAndStatusTrue(id);
        ProductResponseDto productResponse = productMapper.map(product);
        productResponse.setImagesPath(fileService.findByName(product.getImages()));
        return ResponseEntity.ok(productResponse);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> update(ProductUpdateRequestDto productRequestDto, long id) {
        List<Category> categories = categoryService.getByIds(productRequestDto.getCategoriesId());
        log.error("{}", categories);
        Product product = getById(id);
        Product updateProduct = productMapper.map(product,productRequestDto);
        if (categories != null) {
            updateProduct.setCategories(categories);

        }
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> delete(long id) {
        Product product = getById(id);
        product.setStatus(false);
        return ResponseEntity.noContent().build();
    }
}
