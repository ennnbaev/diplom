package com.example.demo;


import com.example.demo.item.domain.Item;
import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.ItemOffer;
import com.example.demo.item.mapper.ItemMapper;
import com.example.demo.item.repo.ItemRepository;
import com.example.demo.item.service.EmailService;
import com.example.demo.item.service.ItemService;
import com.example.demo.user.domain.User;
import com.example.demo.user.repo.UserRepository;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemMapper itemMapper;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createItem_Success() throws Exception {
        // Arrange
        String username = "testUser";
        ItemDto itemDto = new ItemDto();
        User user = new User();
        Item item = new Item();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(itemMapper.toModel(itemDto)).thenReturn(item);

        // Act
        itemService.createItem(username, itemDto);

        // Assert
        verify(userRepository, times(1)).findByUsername(username);
        verify(userRepository, times(1)).save(user);
        verify(itemMapper, times(1)).toModel(itemDto);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void deleteItem_Success() {
        // Arrange
        Long itemId = 1L;

        // Act
        itemService.deleteItem(itemId);

        // Assert
        verify(itemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void updateItem_Success() throws Exception {
        // Arrange
        Long itemId = 1L;
        ItemDto itemDto = new ItemDto();
        Item origItem = new Item();
        origItem.setId(itemId);
        User user = new User();
        Item updatedItem = new Item();

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(origItem));
        when(itemMapper.toModel(itemDto)).thenReturn(updatedItem);

        // Act
        itemService.updateItem(itemId, itemDto);

        // Assert
        verify(itemRepository, times(1)).findById(itemId);
        verify(itemMapper, times(1)).toModel(itemDto);
        assertEquals(itemId, updatedItem.getId());
    }

    @Test
    void createOfferForChange_Success() throws MessagingException {
        // Arrange
        String username = "testUser";
        ItemOffer itemOffer = new ItemOffer();
        itemOffer.setItemId("1");
        Item item = new Item();
        item.setId(1L);
        User user = new User();
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");
        item.setUser(user);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        itemService.createOfferForChange(username, itemOffer);

        // Assert
        verify(emailService, times(1)).sendEmail(eq("test@example.com"), eq("Exchange"),
                contains("Hello!"));
    }

    @Test
    void getAllItems_Success() {
        // Arrange
        List<Item> items = Collections.singletonList(new Item());
        List<ItemDto> itemsDto = Collections.singletonList(new ItemDto());


        when(itemRepository.findAll()).thenReturn(items);
        when(itemMapper.toDtoList(items)).thenReturn(itemsDto);

        // Act
        List<ItemDto> result = itemService.getAllItems("", "");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void getItemInfo_Success() {
        // Arrange
        Item item = new Item();
        item.setId(1L);
        ItemDto itemDto = new ItemDto();
        itemDto.setId(1L);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemMapper.toDto(item)).thenReturn(itemDto);

        // Act
        ItemDto result = itemService.getItemInfo("1");

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }
}
