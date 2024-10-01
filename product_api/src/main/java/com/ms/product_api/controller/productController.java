package com.ms.product_api.controller;

import com.ms.product_api.dto.ProductDTO;
import com.ms.product_api.model.ProductModel;
import com.ms.product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/Product_API")
public class productController {

    final ProductService productService;

    public productController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "save product", description = "Register product on dataBase", tags = "Product_API")
    @PostMapping("/product")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @Operation(summary = "Search product by categoryId", description = "Return all product with the same category", tags = "Product_API")
    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(
            @PathVariable Long categoryId) {
        List<ProductDTO> produtos =
                productService.getProductByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @Operation(summary = "List all Product", description = "List all product who are registered on dataBase", tags = "Product_API")
    @GetMapping("/products")
    public ResponseEntity <List<ProductModel>> getAllProduct(){
        return productService.getAllproducts();
    }

    @Operation(summary = "Delete Product", description = "given in id, delete all information about that Product", tags = "Product_API")
    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable UUID id)
            throws IOException {
        productService.delete(id);
    }

    @Operation(summary = "Search product by productIdentifier", description = "Return only one product", tags = "Product_API")
    @GetMapping("/product/{productIdentifier}")
    public ResponseEntity<ProductDTO>  findById(@PathVariable String productIdentifier) {
        return ResponseEntity.status(HttpStatus.OK).body(productService
                .findByProductIdentifier(productIdentifier));
    }
}