package com.api.stockproject.controllers;

import com.api.stockproject.models.Item;
import com.api.stockproject.services.ItemService;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ItemControllerTest {

    @Test
    void return_created_Item() throws Exception {
        ItemService itemService = mock(ItemService.class);
        Item item = populateItem();
        when(itemService.createItem(item)).thenReturn(item);
        assertNotNull(item);
    }
    @Test
    void return_valid_Item() throws Exception {
        ItemService itemService = mock(ItemService.class);
        String nome = "Glass";
        Item item = populateItem();
        when(itemService.createItem(item)).thenReturn(item);
        assertEquals(item.getName(), nome);
    }

    private Item populateItem(){
        Item item = new Item();
        item.setId(1L);
        item.setName("globe");
        return item;
    }
}