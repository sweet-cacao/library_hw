package ann.ayrapetyan.hw.jdbc;

import ann.ayrapetyan.hw.jdbc.domain.Book;
import ann.ayrapetyan.hw.jdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookShellComponent {
    private final BookService service;

    @Autowired
    public BookShellComponent(BookService service) {
        this.service = service;
    }

    @ShellMethod("get")
    public Book get(@ShellOption final long id) {
        return service.get(id);
    }

    @ShellMethod("get-by-name")
    public Book getByName(@ShellOption final String name) {
        return service.getByName(name);
    }

    @ShellMethod("create")
    public Book create(@ShellOption String bookName, @ShellOption String genre, @ShellOption String name, @ShellOption String surname) {
        return service.create(bookName, genre, name, surname);
    }

    @ShellMethod("update")
    public Book updateName(@ShellOption String bookName, @ShellOption String newBookName) {
        return service.updateName(bookName, newBookName);
    }

    @ShellMethod("getAll")
    public void getAll(){
        service.getAll();
    }

    @ShellMethod("delete")
    public String delete(@ShellOption String bookName) {
        return service.delete(bookName);
    }

    @ShellMethod("getAllComments")
    public void getAllComments(@ShellOption String bookName) {
        service.getAllComments(bookName);
    }

}
