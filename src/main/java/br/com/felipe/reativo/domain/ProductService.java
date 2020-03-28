package br.com.felipe.reativo.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    public Mono<Product> find(String code) {
        return productRepository.findById(code);
    }

    public Mono<Product> update(String code, Product product) {
        return productRepository.findById(code)
                .map(fromDatabase -> this.updateValues(fromDatabase, product))
                .flatMap((Product fromDatabase) -> productRepository.save(fromDatabase));

    }

    private Product updateValues(Product toProduct, Product fromProduct) {
        toProduct.setBrand(fromProduct.getBrand());
        toProduct.setColor(fromProduct.getColor());
        toProduct.setDescription(fromProduct.getDescription());
        toProduct.setPrice(fromProduct.getPrice());

        return toProduct;
    }
}
