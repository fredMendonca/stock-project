package com.api.stockproject.services;

import com.api.stockproject.models.Item;
import com.api.stockproject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Create an item
    @Transactional
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Read all items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Read an item by ID
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));
    }

    // Update an item
    @Transactional
    public Item updateItem(Long itemId, Item updatedItem) {
        Item existingItem = getItemById(itemId);
        existingItem.setName(updatedItem.getName());

        return itemRepository.save(existingItem);
    }

    // Delete an item
    @Transactional
    public void deleteItem(Long itemId) {
        Item item = getItemById(itemId);

        itemRepository.delete(item);
    }
}
