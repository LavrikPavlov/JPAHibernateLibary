package ru.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.services.BookServices;
import ru.library.services.PersonServices;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookServices bookServices;
    private final PersonServices personServices;

    @Autowired
    public BookController(BookServices bookServices, PersonServices personServices) {
        this.bookServices = bookServices;
        this.personServices = personServices;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookServices.findAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookServices.findOne(id));

        Person bookOwner = bookServices.findOne(id).getPersonBook();

        if(bookOwner != null)
            model.addAttribute("owner", bookOwner);
        else
            model.addAttribute("people", personServices.findAll());

        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "book/new";

        bookServices.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookServices.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         @PathVariable("id") int id, BindingResult bandingResult){

        if (bandingResult.hasErrors())
            return "book/edit";

        bookServices.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookServices.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/release")
    public String relese(@PathVariable("id") int id){
        bookServices.release(id);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookServices.assign(id,selectedPerson);
        return "redirect:/book/" + id;
    }
}
