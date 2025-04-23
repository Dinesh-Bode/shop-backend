package com.web.shop.service;

import com.web.shop.mapper.ProductMapper;
import com.web.shop.model.api.ProductRequest;
import com.web.shop.model.api.ProductResponse;
import com.web.shop.model.table.Product;
import com.web.shop.model.table.ProductVariant;
import com.web.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> productResponseList = productList.stream().map(product -> ProductMapper.productProductResponseMapper(product)).toList();
        return productResponseList;
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productDetail -> ProductMapper.productProductResponseMapper(productDetail)).orElse(null);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = getProductFromProductRequest(productRequest);
        product = productRepository.save(product);
        return ProductMapper.productProductResponseMapper(product);
    }

    Product getProductFromProductRequest(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .images(productRequest.getImages())
                .build();

        List<ProductVariant> productVariantList;

        productVariantList = Optional.ofNullable(productRequest.getProductVariants())
                                .orElseGet(Collections::emptyList)
                                .stream()
                                .map(productVariantRequest -> ProductVariant.builder()
                                        .size(productVariantRequest.getSize())
                                        .length(productVariantRequest.getLength())
                                        .price(productVariantRequest.getPrice())
                                        .quantityAvailable(productVariantRequest.getQuantityAvailable())
                                        .product(product)
                                        .build()
                                ).toList();

        product.setProductVariants(productVariantList);

        return product;

    }

}
