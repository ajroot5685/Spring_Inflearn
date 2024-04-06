package com.example.practicestart.controller;

import com.example.practicestart.domain.Item;
import com.example.practicestart.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable(name = "itemId") long itemId, Model model) {
        Item item = itemService.findById(itemId).get();
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemService.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") long itemId, Model model) {
        Item editItem = itemService.findById(itemId).get();

        model.addAttribute("item", editItem);

        return "editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item updatedItem = itemService.update(item);
        redirectAttributes.addAttribute("itemId", updatedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

    @PostMapping("/{itemId}/delete")
    public String delteItem(@PathVariable(name = "itemId") long itemId) {
        itemService.delete(itemId);

        return "redirect:/items";
    }
}
