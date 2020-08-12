package com.vtb.kortunov.lesson25.beans;

import com.vtb.kortunov.lesson25.entities.Book;
import com.vtb.kortunov.lesson25.services.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private Map<String, Integer> purchasedBooks;
    private BookService bookService;

    public int sumAllBooks() {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : purchasedBooks.entrySet()) {
            Integer price = bookService.findByTitle(entry.getKey()).getPrice().intValue();
            sum += (entry.getValue() * price);
        }
        return sum;
    }

    public int getPrice(String title) {
        return bookService.findByTitle(title).getPrice().intValue();
    }
}
