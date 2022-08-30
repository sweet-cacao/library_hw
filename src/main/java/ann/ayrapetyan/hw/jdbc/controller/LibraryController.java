package ann.ayrapetyan.hw.jdbc.controller;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.dto.BookDto;
import ann.ayrapetyan.hw.jdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LibraryController {

    private BookService service;

    @Autowired
    public LibraryController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = service.getAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") int id, Model model) {
        Book book = service.get(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute("book") BookDto book,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        service.updateBook(book.getId(), book);
        return "redirect:/";
    }

    @RequestMapping(value="/books/delete/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new BookDto());
        return "create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute("book") BookDto book,
                             BindingResult bindingResult, Model model) {
        service.create(book.getName(), book.getGenreName(), book.getAuthorName(), book.getAuthorSurname(), book.getComments());
        return "redirect:/";
    }

}
