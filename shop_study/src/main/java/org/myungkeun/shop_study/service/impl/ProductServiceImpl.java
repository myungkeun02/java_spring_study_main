package org.myungkeun.shop_study.service.impl;

import org.modelmapper.ModelMapper;
import org.myungkeun.shop_study.entity.Category;
import org.myungkeun.shop_study.entity.Product;
import org.myungkeun.shop_study.exception.ResourceNotFoundException;
import org.myungkeun.shop_study.payload.ProductDto;
import org.myungkeun.shop_study.payload.ProductsResponseDto;
import org.myungkeun.shop_study.repository.CategoryRepository;
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

    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));

        Product product = mapToEntity(productDto);
        product.setCategory(category);
        Product newProduct = productRepository.save(product);
        ProductDto productResponse = mapToDto(newProduct);
        return productResponse;
    }

    @Override
    public ProductsResponseDto getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();
        List<ProductDto> contents = listOfProducts.stream().map(product ->
                mapToDto(product)).collect(Collectors.toList());
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
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProductById(int id, ProductDto productDto) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDto.getCategoryId()));
        oldProduct.setName(productDto.getName());
        oldProduct.setCategory(category);
        oldProduct.setDetail(productDto.getDetail());
        oldProduct.setPrice(productDto.getPrice());
        Product updateProduct = productRepository.save(oldProduct);
        return mapToDto(updateProduct);
    }

    @Override
    public String deleteProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
        return "deleted";
    }


    private ProductDto mapToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product productEntity = modelMapper.map(productDto, Product.class);
        return productEntity;
    }

}
