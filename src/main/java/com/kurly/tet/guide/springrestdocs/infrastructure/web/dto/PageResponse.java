package com.kurly.tet.guide.springrestdocs.infrastructure.web.dto;

import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {
    private final List<T> content = new ArrayList<>();
    private PageRequest pageRequest;


}
