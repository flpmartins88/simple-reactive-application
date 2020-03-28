package br.com.felipe.reativo.rest;

import br.com.felipe.reativo.domain.Product;
import br.com.felipe.reativo.domain.ProductService;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> save(@RequestBody ProductRequest product) {
        return productService.save(product.toDomain());
    }

    @PutMapping("/{code}")
    public Mono<ResponseEntity<Product>> update(@PathVariable String code, @RequestBody ProductRequest product) {
        return productService.update(code, product.toDomain())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/{code}")
    public Mono<ResponseEntity<Product>> find(@PathVariable String code) {
        return productService.find(code)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> findAll() {
        return productService.findAll();
    }
}
