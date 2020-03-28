package br.com.felipe.reativo;

import br.com.felipe.reativo.domain.Product;
import br.com.felipe.reativo.domain.ProductService;
import br.com.felipe.reativo.rest.ProductController;
import br.com.felipe.reativo.rest.ProductRequest;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
//@WebFluxTest(controllers = ProductController.class)
class ProductControllerTest {

//    @MockBean private ProductService productService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void shouldCreateProduct() {
        ProductRequest productRequest = new ProductRequest("Dell", "Dell Notebook", "Black", 1000000);
        Product product = productRequest.toDomain();

//        BDDMockito.given(productService.save(product)).willReturn(Mono.just(product));

        webTestClient.post().uri("/products")
                .body(BodyInserters.fromValue(productRequest))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.code").exists()
                .jsonPath("$.brand").isEqualTo(productRequest.getBrand())
                .jsonPath("$.description").isEqualTo(productRequest.getDescription())
                .jsonPath("$.color").isEqualTo(productRequest.getColor())
                .jsonPath("$.price").isEqualTo(productRequest.getPrice());

    }

}
