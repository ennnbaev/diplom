package com.example.demo.item.controller;


import com.example.demo.item.dto.ItemDto;
import com.example.demo.item.dto.ItemOffer;
import com.example.demo.item.service.ItemService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("items")
@CrossOrigin(origins = "http://localhost:63342")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("info/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ItemDto getItemInfo(@PathVariable String id) {
        return itemService.getItemInfo(id);
    }

    @GetMapping("info")
    public String getAllItems(Model model, @RequestParam String category, @RequestParam String name) {
        List<ItemDto> items = itemService.getAllItems(category, name);
        model.addAttribute("items", items);
        return "items";
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void deleteItem(@RequestBody ItemDto itemDto) {
        itemService.deleteItem(itemDto.getId());
    }
    @GetMapping("creation")
    public String getItemCreationTemplate() {
        return "createItem";
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void createItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemDto itemDto) throws Exception {
        itemService.createItem(userDetails.getUsername(), itemDto);
    }

    @PostMapping("offer")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void createOfferForChange(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemOffer itemOffer) throws MessagingException {
        itemService.createOfferForChange(userDetails.getUsername(),itemOffer);
    }

    @PatchMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void updateItem(@RequestBody ItemDto itemDto) throws Exception {
        itemService.updateItem(itemDto.getId(), itemDto);
    }

}
