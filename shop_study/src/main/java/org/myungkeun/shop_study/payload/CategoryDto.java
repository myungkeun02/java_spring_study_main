package org.myungkeun.shop_study.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int id;
    private String name;
    private String description;
}
