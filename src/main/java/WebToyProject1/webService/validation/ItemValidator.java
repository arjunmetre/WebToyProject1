package WebToyProject1.webService.validation;

import WebToyProject1.webService.domain.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }

        if (item.getPrice() == null || item.getPrice() > 1000 || item.getPrice() < 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        if (item.getRegions().isEmpty()) {
            errors.rejectValue("regions", "min", new Object[]{1}, null);
        }

        if (item.getItemType() == null) {
            errors.rejectValue("itemType", "required");

        }

        if (!StringUtils.hasText(item.getDeliveryCode())) {
            errors.rejectValue("deliveryCode", "required");
        }

        // global
        if (item.getQuantity() != null && item.getPrice() != null) {
            long result = item.getPrice() + item.getQuantity();
            if (result < 10000) {
                errors.reject("totalPriceMin", new Object[] {10000, result}, null);
            }
        }
    }
}
