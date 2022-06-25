package WebToyProject1.webService.domain.repository;

import WebToyProject1.webService.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static Map<Long, Item> store = new HashMap<>();
    private long sequence = 0;

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

    public Item update(Long itemId, Item item) {
        Item originItem = findById(itemId);

        originItem.setItemName(item.getItemName());
        originItem.setPrice(item.getPrice());
        originItem.setQuantity(item.getQuantity());
        originItem.setOpen(item.getOpen());
        originItem.setRegions(item.getRegions());
        originItem.setItemType(item.getItemType());
        originItem.setDeliveryCode(item.getDeliveryCode());

        return originItem;
    }

}
