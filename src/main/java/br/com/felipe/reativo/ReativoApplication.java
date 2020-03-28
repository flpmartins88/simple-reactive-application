package br.com.felipe.reativo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReativoApplication {

    public static void main(String[] args) {

        final Flux<String> brands = Flux
                .just("Under Armour", "Asics", "Nike", "Adidas", "Mizuno");

        brands.sort().subscribe(System.out::println); //all sorted items

        SpringApplication.run(ReativoApplication.class, args);
    }

}
