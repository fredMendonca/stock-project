package com.api.stockproject.controllers;

import com.api.stockproject.models.Item;
import com.api.stockproject.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Create an item
    @PostMapping("/")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // Read all items
    @GetMapping("/")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Read an item by ID
    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    // Update an item
    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        return itemService.updateItem(itemId, item);
    }

    // Delete an item
    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
    }
}

