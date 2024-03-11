package org.myungkeun.shop_study.service;

import org.myungkeun.shop_study.payload.ProductDto;
import org.myungkeun.shop_study.payload.ProductsResponseDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductDto getProductById(int id);

    ProductDto updateProductById(int id, ProductDto productDto);

    String deleteProductById(int id);
}
