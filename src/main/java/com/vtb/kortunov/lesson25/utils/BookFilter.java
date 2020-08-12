package com.vtb.kortunov.lesson25.utils;

import com.vtb.kortunov.lesson25.entities.Book;
import com.vtb.kortunov.lesson25.repositories.specifications.BookSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class BookFilter {
    private Specification<Book> spec;
    private String filterParams;

    public BookFilter(Map<String, String> params) {
        spec = Specification.where(null);
        StringBuilder parameters = new StringBuilder("&");

        if (params.containsKey("genre") && params.get("genre") != null) {
            spec = spec.or(BookSpecifications.genreLike(params.get("genre")));
            parameters.append("genre=").append(params.get("genre")).append('&');
        }
        if (params.containsKey("maxPrice") && !params.get("maxPrice").equals("")) {
            spec = spec.and(BookSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.get("maxPrice"))));
            parameters.append("maxPrice=").append(params.get("maxPrice")).append('&');
        }
        if (params.containsKey("minPrice") && !params.get("minPrice").equals("")) {
            spec = spec.and(BookSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.get("minPrice"))));
            parameters.append("minPrice=").append(params.get("minPrice")).append('&');
        }
        if (params.containsKey("titlePart") && !params.get("titlePart").equals("")) {
            spec = spec.and(BookSpecifications.titleLike(params.get("titlePart")));
            parameters.append("titlePart=").append(params.get("titlePart")).append('&');
        }
        if (!parameters.toString().equals("&")) {
            this.filterParams = parameters.subSequence(0, parameters.length() - 1).toString();
        } else {
            this.filterParams = "";
        }
    }
}
