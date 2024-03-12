package org.myungkeun.blog_study.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.myungkeun.blog_study.entity.Category;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private int price;
}
