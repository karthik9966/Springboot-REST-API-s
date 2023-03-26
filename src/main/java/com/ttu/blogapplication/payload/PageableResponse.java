package com.ttu.blogapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableResponse {
    private List<PostDTO> postDTOS;
    private int pageNumber;
    private long totalItems;
    private int totalPages;
    private boolean isLastPage;
}
