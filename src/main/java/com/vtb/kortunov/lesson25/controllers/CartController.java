package com.vtb.kortunov.lesson25.controllers;

import com.vtb.kortunov.lesson25.beans.Cart;
import com.vtb.kortunov.lesson25.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private Cart cart;
    private OrderItemService orderItemService;

    @GetMapping
    public String showPurchases(Model model) {
        model.addAttribute("purchased", cart.getPurchasedBooks());
        model.addAttribute("cart", cart);
        model.addAttribute("sum", cart.sumAllBooks());
        return "cart-page";
    }

    @GetMapping("/buy")
    public String buyBooks() {
        orderItemService.saveOrderItems();
        return "success";
    }

}
