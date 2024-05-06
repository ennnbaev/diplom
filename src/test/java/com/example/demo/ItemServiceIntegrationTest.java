package com.example.demo;

import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.repo.ItemRepository;
import com.example.demo.item.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ItemServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @Test
    public void testCreateItem() throws Exception {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("Test Item");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Test Item\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/items?"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetItemInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/items/1"))
                .andExpect(status().isOk());
    }
}

