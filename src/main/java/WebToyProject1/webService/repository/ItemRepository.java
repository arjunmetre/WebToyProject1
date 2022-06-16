package WebToyProject1.webService.repository;

import WebToyProject1.webService.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private Map<Long, Item> store = new HashMap<>();
    private long sequence = 0;

    @Autowired
    public ItemRepository() {
    }

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Item update(Item item) {
        Item originItem = findById(item.getId());

        originItem.setItemName(item.getItemName());
        originItem.setPrice(item.getPrice());
        originItem.setQuantity(item.getQuantity());

        return item;
    }

    @PostConstruct
    public void init() {
        Item item1 = new Item("item1", 30000L, 30L);
        Item item2 = new Item("item2", 20000L, 40L);

        save(item1);
        save(item2);
    }
}
