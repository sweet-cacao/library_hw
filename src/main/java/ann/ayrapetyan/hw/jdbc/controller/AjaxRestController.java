package ann.ayrapetyan.hw.jdbc.controller;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ajaxrest")
public class AjaxRestController {

    private BookService service;

    @Autowired
    public AjaxRestController(BookService service) {
        this.service = service;
    }


    @RequestMapping(
            value = "getBook/{id}",
            method = RequestMethod.GET,
            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        try {
            ResponseEntity<Book> entity = new ResponseEntity<>(service.get(id) ,HttpStatus.OK);
            return entity;
        } catch (Exception e) {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
        }
    }

}
