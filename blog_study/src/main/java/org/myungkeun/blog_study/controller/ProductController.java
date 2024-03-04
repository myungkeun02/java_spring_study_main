package org.myungkeun.blog_study.controller;

import org.myungkeun.blog_study.entity.ProductEntity;
import org.myungkeun.blog_study.payload.ProductDto;
import org.myungkeun.blog_study.payload.ProductsResponseDto;
import org.myungkeun.blog_study.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/api")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/creata")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<ProductsResponseDto> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable(name = "id") String id, @RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.updateProductById(id, productDto), HttpStatus.OK);
    }
}
