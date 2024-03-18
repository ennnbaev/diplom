package com.example.demo.item.repo;


import com.example.demo.item.domain.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findByName(String name);

    List<Item> findByCategory(String category);

    List<Item> findByNameAndCategory(String name, String category);
}
