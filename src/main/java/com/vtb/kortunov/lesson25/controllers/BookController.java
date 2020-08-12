package com.vtb.kortunov.lesson25.controllers;

import com.vtb.kortunov.lesson25.beans.Cart;
import com.vtb.kortunov.lesson25.entities.Book;
import com.vtb.kortunov.lesson25.entities.Genre;
import com.vtb.kortunov.lesson25.services.BookService;
import com.vtb.kortunov.lesson25.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private Cart cart;
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam Map<String, String> params
    ) {
        BookFilter bookFilter = new BookFilter(params);
        model.addAttribute("currentPage", pageIndex);
        Page<Book> page = bookService.findAll(bookFilter.getSpec(), pageIndex - 1, 5);
        model.addAttribute("books", page.getContent());
        model.addAttribute("filterParameters", bookFilter.getFilterParams());
        model.addAttribute("page", page);
        model.addAttribute("genres", Genre.values());
        return "store-page";
    }

    @GetMapping("/addToCart/{title}")
    public String showEditProduct(@PathVariable String title, Model model) {
        Book book = bookService.findByTitle(title);
        int count = 1;
        Map<String, Integer> purchases = cart.getPurchasedBooks();
        if (purchases.containsKey(book.getTitle())) {
            count = purchases.get(book.getTitle()) + 1;
        }
        purchases.put(book.getTitle(), count);
        cart.setPurchasedBooks(purchases);
        model.addAttribute("cart", cart.getPurchasedBooks());
        return "redirect:/books";
    }
}
