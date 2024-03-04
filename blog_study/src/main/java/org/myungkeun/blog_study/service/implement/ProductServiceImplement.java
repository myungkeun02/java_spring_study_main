package org.myungkeun.blog_study.service.implement;

import org.modelmapper.ModelMapper;
import org.myungkeun.blog_study.entity.ProductEntity;
import org.myungkeun.blog_study.exception.ResourceNotFoundException;
import org.myungkeun.blog_study.payload.ProductDto;
import org.myungkeun.blog_study.payload.ProductsResponseDto;
import org.myungkeun.blog_study.repository.ProductRepository;
import org.myungkeun.blog_study.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplement implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductServiceImplement(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = mapToEntity(productDto);
        ProductEntity newProduct = productRepository.save(productEntity);
        ProductDto productResponse = mapToDto(newProduct);
        return productResponse;
    }

    @Override
    public ProductsResponseDto getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> contents = products.stream().map(productEntity ->
                mapToDto(productEntity)).collect(Collectors.toList());
        ProductsResponseDto productsResponse = new ProductsResponseDto();
        productsResponse.setContent(contents);
        return productsResponse;
    }

    @Override
    public ProductDto getProductById(String id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }



    @Override
    public ProductDto updateProductById(String id, ProductDto productDto) {
        ProductEntity oldProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        oldProduct.setName(productDto.getName());
        oldProduct.setCategory(productDto.getCategory());
        oldProduct.setDescription(productDto.getDescription());
        oldProduct.setPrice(productDto.getPrice());
        ProductEntity updateProduct = productRepository.save(oldProduct);
        return mapToDto(updateProduct);
    }

    @Override
    public String deleteProductById(String id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
        return "deleted";
    }

    private ProductDto mapToDto(ProductEntity productEntity) {
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        return productDto;
    }

    private ProductEntity mapToEntity(ProductDto productDto) {
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        return productEntity;
    }
}
