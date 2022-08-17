package ann.ayrapetyan.hw.jdbc.controller;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.jpa.ReactiveBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/flux")
public class WebfluxController {

    private ReactiveBookRepository service;

    @Autowired
    public WebfluxController(ReactiveBookRepository service) {
        this.service = service;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public Flux<Book> getBooks() {
        return service.findAll().delayElements(Duration.ofSeconds(2));
    }
}
