package com.pi.yamam.controller;

import com.pi.yamam.domain.images.DTO.ImageRequestDTO;
import com.pi.yamam.domain.product.Product;
import com.pi.yamam.domain.product.DTO.DTOProductRequest;
import com.pi.yamam.domain.product.DTO.DTOResponseProduct;
import com.pi.yamam.domain.user.User;
import com.pi.yamam.infra.util.UploadUtil;
import com.pi.yamam.repositories.ProductRepository;
import com.pi.yamam.service.ImageService;
import com.pi.yamam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageController imageController;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody DTOProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public List<DTOResponseProduct> listProduct() {
        return productRepository.findAll().stream()
                .map(product -> new DTOResponseProduct(product.getId(), product.getName(),
                        product.getDescription(), product.getRating(), product.getStatus(), product.getPrice(),
                        product.getStock()))
                .collect(Collectors.toList());
    }

    @PutMapping("/status/{id}")
    public ResponseEntity updateStatus(@PathVariable long id) {
        try {
            Product product = productService.updateStatus(id);
            return ResponseEntity.ok().body(product);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Error while at updating status");
        }
    }
}
