package com.kuldeep.blog.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
    private List<CategoryDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private Boolean isLastPage;
    private int totalPages;
}


