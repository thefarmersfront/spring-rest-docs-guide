package com.kurly.tet.guide.springrestdocs.infrastructure.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringRestDocsRestController {
    @GetMapping("/spring-rest-docs")
    public void getSpringRestDocs() {
    }
}
