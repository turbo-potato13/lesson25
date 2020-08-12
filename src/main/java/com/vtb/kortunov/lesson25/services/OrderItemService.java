package com.vtb.kortunov.lesson25.services;

import com.vtb.kortunov.lesson25.beans.Cart;
import com.vtb.kortunov.lesson25.entities.OrderItem;
import com.vtb.kortunov.lesson25.repositories.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrderItemService {
    private OrderItemRepository orderItemRepository;
    private BookService bookService;
    private Cart cart;
    private List<OrderItem> orderItems;

    public void addToLists() {
        Map<String, Integer> purchasedBooks = cart.getPurchasedBooks();
        for (Map.Entry<String, Integer> entry : purchasedBooks.entrySet()) {
            OrderItem orderItem = new OrderItem();
            int price = bookService.findByTitle(entry.getKey()).getPrice().intValue();
            orderItem.setCount(entry.getValue());
            orderItem.setPrice(price * entry.getValue());
            orderItems.add(orderItem);
        }
    }

    public void saveOrderItems() {
        addToLists();
        orderItemRepository.saveAll(orderItems);
    }

}
