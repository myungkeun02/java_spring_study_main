package org.myungkeun.shop_study.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String category;
    private String detail;
    private int quantity;
    private int price;
}
