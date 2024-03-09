package org.myungkeun.blog_study.service.implement;

import org.modelmapper.ModelMapper;
import org.myungkeun.blog_study.entity.Category;
import org.myungkeun.blog_study.entity.Product;
import org.myungkeun.blog_study.exception.ResourceNotFoundException;
import org.myungkeun.blog_study.payload.ProductDto;
import org.myungkeun.blog_study.payload.ProductsResponseDto;
import org.myungkeun.blog_study.repository.CategoryRepository;
import org.myungkeun.blog_study.repository.ProductRepository;
import org.myungkeun.blog_study.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplement implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public ProductServiceImplement(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            ModelMapper modelMapper
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", productDto.getCategoryId()));
        Product productEntity = mapToEntity(productDto);
        productEntity.setCategory(category);
        Product newProduct = productRepository.save(productEntity);
        ProductDto productResponse = mapToDto(newProduct);
        return productResponse;
    }




    @Override
    public ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();
        List<ProductDto> contents = listOfProducts.stream().map(productEntity ->
                mapToDto(productEntity)).collect(Collectors.toList());
        ProductsResponseDto productsResponse = new ProductsResponseDto();
        productsResponse.setContent(contents);
        productsResponse.setPageNo(products.getNumber() + 1);
        productsResponse.setPageSize(products.getSize());
        productsResponse.setTotalPages(products.getTotalPages());
        productsResponse.setTotalElements(products.getTotalElements());
        productsResponse.setLast(products.isLast());
        return productsResponse;
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }



    @Override
    public ProductDto updateProductById(long id, ProductDto productDto) {

        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", productDto.getCategoryId()));
        oldProduct.setName(productDto.getName());
        oldProduct.setCategory(category);
        oldProduct.setDescription(productDto.getDescription());
        oldProduct.setPrice(productDto.getPrice());
        Product updateProduct = productRepository.save(oldProduct);
        return mapToDto(updateProduct);
    }

    @Override
    public String deleteProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
        return "deleted";
    }

    private ProductDto mapToDto(Product productEntity) {
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product productEntity = modelMapper.map(productDto, Product.class);
        return productEntity;
    }
}
