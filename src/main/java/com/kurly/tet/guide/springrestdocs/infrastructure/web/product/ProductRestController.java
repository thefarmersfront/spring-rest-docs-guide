package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.ProductDto;
import com.kurly.tet.guide.springrestdocs.domain.exception.ProductNotFoundException;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductRestController {
    private final AtomicLong productIdCreator = new AtomicLong(0);
    private final List<ProductDto> products = new ArrayList<>();

    @PostConstruct
    public void setUp() {
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%5d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%5d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%5d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%5d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%5d", productIdCreator.incrementAndGet())));
    }

    @GetMapping("/products")
    public PageResponse<ProductDto> search(ProductSearchCondition searchCondition, PageRequest pageRequest) {
        return new PageResponse<>(this.products, pageRequest, this.products.size());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductCreateCommand createCommand) {
        var createProduct = createCommand.createDto(productIdCreator.incrementAndGet());

        products.add(createProduct);
        return createProduct;
    }

    @GetMapping("/products/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {
        return products.stream()
                .filter(it -> it.getProductId().equals(productId)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @PutMapping("/products/{productId}")
    public ProductDto modifyProduct(@PathVariable Long productId, @Valid @RequestBody ProductModifyCommand modifyCommand) {
        return products.stream()
                .filter(it -> it.getProductId().equals(productId)).findFirst()
                .map(modifyCommand::modify)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        products.removeIf(it -> it.getProductId().equals(productId));
    }

}
