package com.pi.yamam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pi.yamam.domain.images.DTO.ImageRequestDTO;
import com.pi.yamam.domain.product.Product;
import com.pi.yamam.domain.product.DTO.DTOProductRequest;
import com.pi.yamam.repositories.ProductRepository;
import com.pi.yamam.service.ImageService;

@RestController
@RequestMapping("images")
public class ImageController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/{id}")
    public ResponseEntity uploadImages(@RequestParam("img") List<MultipartFile> files, @PathVariable Long id ) {

        // Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR"));
        imageService.uploadImages(files,id );
        // boolean teste = imageService.insertImage(files,new ImageRequestDTO(file.getOriginalFilename(), true, product));
        return ResponseEntity.ok("teste");

    }
}
