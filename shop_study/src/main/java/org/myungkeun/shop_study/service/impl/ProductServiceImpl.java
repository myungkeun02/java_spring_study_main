package org.myungkeun.shop_study.service.impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.shop_study.entity.ProductEntity;
import org.myungkeun.shop_study.exception.ResourceNotFoundException;
import org.myungkeun.shop_study.payload.ProductDto;
import org.myungkeun.shop_study.payload.ProductsResponseDto;
import org.myungkeun.shop_study.repository.ProductRepository;
import org.myungkeun.shop_study.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = mapToEntity(productDto);
        ProductEntity newProduct = productRepository.save(productEntity);
        ProductDto productResponse = mapToDto(newProduct);
        return productResponse;
    }

    @Override
    public ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ProductEntity> products = productRepository.findAll(pageable);
        List<ProductEntity> listOfProducts = products.getContent();
        List<ProductDto> contents = listOfProducts.stream().map(productEntity ->
                mapToDto(productEntity)).collect(Collectors.toList());
        ProductsResponseDto responseProducts = new ProductsResponseDto();
        responseProducts.setContent(contents);
        responseProducts.setPageNo(products.getNumber() + 1);
        responseProducts.setPageSize(products.getSize());
        responseProducts.setTotalPages(products.getTotalPages());
        responseProducts.setTotalElements(products.getTotalElements());
        responseProducts.setLast(products.isLast());
        return responseProducts;
    }

    @Override
    public ProductDto getProductById(int id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProductById(int id, ProductDto productDto) {
        ProductEntity oldProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        oldProduct.setName(productDto.getName());
        oldProduct.setCategory(productDto.getCategory());
        oldProduct.setDetail(productDto.getDetail());
        oldProduct.setPrice(productDto.getPrice());
        ProductEntity updateProduct = productRepository.save(oldProduct);
        return mapToDto(updateProduct);
    }

    @Override
    public String deleteProductById(int id) {
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
