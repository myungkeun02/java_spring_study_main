package org.myungkeun.blog_study.payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.myungkeun.blog_study.entity.Category;

@Data
public class ProductDto {
    private Long id;
//    @NotEmpty
//    @Size(min = 2, max = 20, message = "Product title should have at least 2")
    private String name;

    private Long categoryId;

//    @NotEmpty
//    @Size(min = 2, max = 20, message = "Product title should have at least 2")
    private String description;

//    @NotEmpty
    private int price;
}
