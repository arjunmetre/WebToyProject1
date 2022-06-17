package WebToyProject1.webService;

import WebToyProject1.webService.domain.Item;
import WebToyProject1.webService.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final ItemRepository itemRepository;


    @PostConstruct
    public void init() {
        Item item1 = new Item("item1", 30000L, 30L);
        Item item2 = new Item("item2", 20000L, 40L);

        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}
