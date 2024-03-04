package org.myungkeun.blog_study.service;

import org.myungkeun.blog_study.payload.ProductDto;
import org.myungkeun.blog_study.payload.ProductsResponseDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductsResponseDto getAllProducts();

    ProductDto getProductById(String id);

    ProductDto updateProductById(String id, ProductDto productDto);

    String deleteProductById(String id);
}