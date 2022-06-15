package WebToyProject1.webService.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Long price;
    private Long quantity;

    public Item() {

    }

    public Item(String itemName, Long price, Long quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
