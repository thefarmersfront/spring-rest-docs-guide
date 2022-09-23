package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.ProductDto;
import com.kurly.tet.guide.springrestdocs.domain.ProductFacade;
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
    private final ProductFacade productFacade;

    public ProductRestController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }


    @GetMapping("/products")
    public PageResponse<ProductDto> search(ProductSearchCondition searchCondition, @Valid PageRequest pageRequest) {
        return productFacade.search(searchCondition, pageRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/products")
    public ProductDto createProduct(@Valid @RequestBody ProductCreateCommand createCommand) {
        return productFacade.create(createCommand);
    }

    @GetMapping("/products/{productId}")
    public ProductDto getProduct(@PathVariable("productId") Long productId) {
        return productFacade.getProduct(productId);
    }


    @PutMapping("/products/{productId}")
    public ProductDto modifyProduct(@PathVariable Long productId, @Valid @RequestBody ProductModifyCommand modifyCommand) {
        return productFacade.modify(productId, modifyCommand);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productFacade.remove(productId);
    }

}
