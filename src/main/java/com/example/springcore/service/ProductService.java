package com.example.springcore.service;

import com.example.springcore.model.Product;
import com.example.springcore.dto.ProductMypriceRequestDto;
import com.example.springcore.repository.ProductRepository;
import com.example.springcore.dto.ProductRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.save(product);

        return product;

    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto)  {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));


        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();

        return products;


    }
}