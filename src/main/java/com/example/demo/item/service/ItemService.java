package com.example.demo.item.service;


import com.example.demo.item.domain.Item;
import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.ItemOffer;
import com.example.demo.item.mapper.ItemMapper;
import com.example.demo.item.repo.ItemRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repo.UserRepository;
import com.example.demo.user.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;

    public void createItem(String username, ItemDto itemDto) throws Exception {
        Item item = itemMapper.toModel(itemDto);

// Сохраните объект User
        User user = userRepository.findByUsername(username).orElseThrow();
        User savedUser = userRepository.save(user); // сохранение пользователя и получение сохраненного экземпляра с идентификатором

// Установите связь между Item и User
        item.setUser(savedUser);

// Сохраните объект Item
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public void updateItem(Long id, ItemDto itemDto) throws Exception {
        Item origData = itemRepository.findById(id).orElseThrow(Exception::new);
        Item item = itemMapper.toModel(itemDto);
        item.setId(id);
        item.setUser(origData.getUser());
        itemRepository.save(item);
    }

    public void createOfferForChange(String username, ItemOffer itemOffer) throws MessagingException {
        Item item = itemRepository.findById(Long.parseLong(itemOffer.getItemId())).orElseThrow();
        User user = userRepository.findByUsername(username).orElseThrow();
        String htmlBody = "<h1>Hello!</h1><p>It's message from Obmin. User with name: " + username + " and contact info: " + user.getPhoneNumber() + " wants  to change you, please write him if it's ok . Item name is: " + item.getName() + "</p>";
        emailService.sendEmail(item.getUser().getEmail(), "Exchange", htmlBody);

    }

    public List<ItemDto> getAllItems(String category, String name) {
        if (!category.isBlank() && !name.isBlank()) {
            return itemMapper.toDtoList(itemRepository.findByNameAndCategory(name, category));
        } else if (!category.isBlank()) {
            return itemMapper.toDtoList(itemRepository.findByCategory(category));
        } else if (!name.isBlank()) {
            return itemMapper.toDtoList(itemRepository.findByName(name));
        } else {
            return itemMapper.toDtoList((List<Item>) itemRepository.findAll());
        }
    }

    public ItemDto getItemInfo(String id) {
        return itemMapper.toDto(itemRepository.findById(Long.parseLong(id)).orElseThrow());
    }
}
