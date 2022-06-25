package WebToyProject1.webService.web.form;

import WebToyProject1.webService.domain.item.ItemType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ItemSaveForm {

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
}
