package com.example.reservationservice;

import com.mongodb.client.MongoDatabase;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReservationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routes(ReservationHandler handler) {
        return route(GET("/reservations/{id}"), handler::byId);
    }
}

@Component
class Initializer implements ApplicationRunner {
    public Initializer(ReservationRepository repo) {
        this.repo = repo;
    }
    private final ReservationRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.just("A", "B", "C")
                .map( name -> new Reservation(null, name))
                .flatMap( r -> this.repo.save(r))
                .subscribe(r-> System.out.println("reservation: " + r.toString()));
    }
}
@Component
class ReservationHandler {
    private final ReservationRepository repository;

    public ReservationHandler(ReservationRepository repository) {
        this.repository = repository;
    }

    Mono<ServerResponse> byId(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Reservation> byIdResults = this.repository.findById(id);
        return ServerResponse.ok().body(byIdResults, Reservation.class);
    }
}


@RestController
class ReservationRestController {
    private final ReservationRepository repository;

    public ReservationRestController(ReservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reservations")
    Flux<Reservation> all() {
        return this.repository.findAll();
    }
}

interface ReservationRepository extends ReactiveMongoRepository<Reservation, String> {
    Flux<Reservation> findByReservationName(String rn);
}
//@Repository
//class ReservationRepository {
//    private final ReactiveMongoTemplate template;
//
//    public ReservationRepository(ReactiveMongoTemplate template) {
//        this.template = template;
//    }
//
//    public Flux<Reservation> findAll() {
//        return this.template.findAll(Reservation.class);
//    }
//}

//@Component
//class ReservationHandler implements HandlerFunction<ServerResponse> {
//    @Override
//    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
//        return null;
//    }
//}

//@Configuration
//class ReservationConfiguration {
//    @Bean
//    MongoDatabase database() {
//        return null;
//    }
//}