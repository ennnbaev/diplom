package com.example.demo.item.mapper;


import com.example.demo.item.domain.Item;
import com.example.demo.item.dto.ItemDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemDto toDto (Item item);

    Item toModel(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> items);
}
