package WebToyProject1.webService.domain.repository;

import WebToyProject1.webService.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @Test
    void saveTest() {
        //given
        Item item = new Item();

        //when
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(savedItem.getId()).isEqualTo(item.getId());
    }

    @Test
    void findByIdTest() {
        //given
        Item item = new Item("pringles1", 3L, 10L);
        Item savedItem = itemRepository.save(item);

        //when
        Item findedItem = itemRepository.findById(1L);

        //then
        assertThat(savedItem.getId()).isEqualTo(findedItem.getId());
    }
}