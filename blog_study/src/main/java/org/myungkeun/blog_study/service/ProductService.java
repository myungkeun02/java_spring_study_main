package org.myungkeun.blog_study.service;

import org.myungkeun.blog_study.payload.ProductDto;
import org.myungkeun.blog_study.payload.ProductsResponseDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

    ProductDto getProductById(long id);

    ProductDto updateProductById(long id, ProductDto productDto);

    String deleteProductById(long id);
}