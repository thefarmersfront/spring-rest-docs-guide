package com.kurly.tet.guide.springrestdocs.infrastructure.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
    @GetMapping("/products")
    public void products() {}
}
