package com.vtb.kortunov.lesson25.repositories.specifications;

import com.vtb.kortunov.lesson25.entities.Book;
import com.vtb.kortunov.lesson25.entities.Genre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {
    public static Specification<Book> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);  // where b.price >= minPrice
    }

    public static Specification<Book> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice); // where b.price <= maxPrice
    }

    public static Specification<Book> titleLike(String titlePart) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where b.title like %titlePart%
    }

    public static Specification<Book> genreLike(String genre) {
        return (Specification<Book>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), Genre.valueOf(genre));
    }
}
