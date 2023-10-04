package com.api.stockproject.services;

import com.api.stockproject.models.Item;
import com.api.stockproject.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Test
    void return_saved_Item_() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        Item item = populateItem();
        when(itemRepository.save(item)).thenReturn(item);
        assertNotNull(item);
    }

    @Test
    void valid_saved_Item() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        String itemTestName = "Glass";
        Item item = populateItem();
        when(itemRepository.save(item)).thenReturn(item);
        assertNotEquals(itemTestName, item.getName());
    }

    private Item populateItem(){
        Item item = new Item();
        item.setId(1L);
        item.setName("globe");
        return item;
    }
}