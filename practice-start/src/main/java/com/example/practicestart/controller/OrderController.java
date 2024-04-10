package com.example.practicestart.controller;

import com.example.practicestart.domain.Item;
import com.example.practicestart.domain.Order;
import com.example.practicestart.service.ItemService;
import com.example.practicestart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/add")
    public String createForm(Model model) {

        List<Item> items = itemService.findItems();

        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/add")
    public String order(@RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("")
    public String orderList(Model model) {
        List<Order> orders = orderService.findOrders();

        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){

        orderService.cancelOrder(orderId);

        return "redirect:/orders";
    }

}
