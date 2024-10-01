package com.ms.product_api.service;

import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.ms.product_api.dto.ProductDTO;
import com.ms.product_api.model.CategoryModel;
import com.ms.product_api.model.ProductModel;
import com.ms.product_api.repository.CategoryRepository;
import com.ms.product_api.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<Object> saveProduct(ProductDTO productDTO){
        Optional<CategoryModel> categoryModel = categoryRepository.findById(productDTO.category().idCategory());
        if(categoryModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("category not exist");        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDTO, productModel);
        productModel.setCategory(categoryModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    public ResponseEntity<List<ProductModel>> getAllproducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    public List<ProductDTO> getProductByCategoryId(
            Long categoryId) {
        List<ProductModel> products =
                productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(
            String productIdentifier) {
        ProductModel product =
                productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return ProductDTO.convert(product);
        }
        return null;
    }

    public ResponseEntity<Object> getProductById(UUID idProduct){
        Optional<ProductModel> productModel = productRepository.findById(idProduct);
        if (productModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModel.get());
    }

    public void delete(UUID productId) {
        Optional<ProductModel> product =
                productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        }
    }

    public ProductDTO getProductByIdentifier(String productIdentifier) {
        RestTemplate restTemplate = new RestTemplate();
        String url =
                "http://localhost:8081/product/" + productIdentifier;
        ResponseEntity<ProductDTO> response =
                restTemplate.getForEntity(url, ProductDTO.class);
        return response.getBody();
    }
}
