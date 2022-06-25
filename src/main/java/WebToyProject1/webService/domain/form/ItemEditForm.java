package WebToyProject1.webService.domain.form;

import WebToyProject1.webService.domain.ItemType;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ItemEditForm {

    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Long price;

    @NotNull
    private Long quantity;

    private Boolean open;

    @Size(min = 1)
    private List<String> regions;
    private ItemType itemType;

    @NotBlank
    private String deliveryCode;
}
