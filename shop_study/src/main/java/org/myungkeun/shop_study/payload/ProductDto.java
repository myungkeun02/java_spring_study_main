package org.myungkeun.shop_study.payload;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private int categoryId;
    private String detail;
    private int quantity;
    private int price;
}
