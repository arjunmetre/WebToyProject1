package WebToyProject1.webService.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Component
@Getter @Setter
public class Item {

    private Long id;

    @NotBlank
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 1000000)
    private Long price;

    @NotNull
    @Max(9999)
    private Long quantity;

    private Boolean open;

    @Size(min = 1)
    private List<String> regions;
    private ItemType itemType;

    @NotBlank
    private String deliveryCode;

    public Item() {

    }

    public Item(String itemName, Long price, Long quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
