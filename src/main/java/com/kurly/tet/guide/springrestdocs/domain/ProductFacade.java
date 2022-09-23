package com.kurly.tet.guide.springrestdocs.domain;

import com.kurly.tet.guide.springrestdocs.domain.exception.ProductNotFoundException;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.product.ProductCreateCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.product.ProductModifyCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.product.ProductSearchCondition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 대충...
 */
@Component
public class ProductFacade {
    private final AtomicLong productIdCreator = new AtomicLong(0);
    private final List<ProductDto> products = new ArrayList<>();

    @PostConstruct
    public void setUp() {
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%05d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%05d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%05d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%05d", productIdCreator.incrementAndGet())));
        products.add(new ProductDto(productIdCreator.incrementAndGet(), "TEST" + productIdCreator.incrementAndGet(), String.format("%05d", productIdCreator.incrementAndGet())));
    }

    public PageResponse search(ProductSearchCondition searchCondition, PageRequest pageRequest) {
        return new PageResponse<>(this.products, pageRequest, this.products.size());
    }

    public ProductDto create(ProductCreateCommand createCommand) {
        var createProduct = createCommand.createDto(productIdCreator.incrementAndGet());

        products.add(createProduct);

        return createProduct;
    }

    public ProductDto getProduct(Long productId) {
        return products.stream()
                .filter(it -> it.getProductId().equals(productId)).findFirst()
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public ProductDto modify(Long productId, ProductModifyCommand modifyCommand) {
        var targetProduct = getProduct(productId);
        modifyCommand.modify(targetProduct);

        return targetProduct;
    }

    public void remove(Long productId) {
        products.removeIf(it -> it.getProductId().equals(productId));
    }
}
