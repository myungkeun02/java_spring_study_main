package org.myungkeun.blog_study.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String category;
    private String description;
    private int price;
}
