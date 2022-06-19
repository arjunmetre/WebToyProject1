package WebToyProject1.webService.controller;

import WebToyProject1.webService.domain.DeliveryCode;
import WebToyProject1.webService.domain.Item;
import WebToyProject1.webService.domain.ItemType;
import WebToyProject1.webService.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();

        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");

        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCode() {

        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("QUICK", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));

        return deliveryCodes;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/items")
    public String items(Model model) {

        model.addAttribute("data", itemRepository.findAll());

        return "domain/items";
    }

    @GetMapping("/item/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);

        model.addAttribute("item", item);

        return "domain/item";
    }

    @GetMapping("/item/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "domain/addItem";
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "이름은 필수값 입니다."));
        }

        if (item.getPrice() == null || item.getPrice() < 1000) {
            bindingResult.addError(new FieldError("item", "price", "가격은 필수 값이며, 1,000원 이상이어야 합니다."));
        }

        if (item.getQuantity() == null || item.getQuantity() < 10) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 필수 값이며, 10개 이상이어야 합니다."));
        }

        if (item.getItemType() == null) {
            bindingResult.addError(new FieldError("item", "itemType", "아이템 타입을 선택해주세요."));
        }

        if (item.getRegions().isEmpty()) {
            bindingResult.addError(new FieldError("item", "regions", "지역을 한개 이상 선택해주세요."));
        }

        if (!StringUtils.hasText(item.getDeliveryCode())) {
            bindingResult.addError(new FieldError("item", "deliveryCode", "배송 방식을 선택해주세요."));
        }

        // global
        if (item.getQuantity() != null && item.getPrice() != null) {
            long result = item.getPrice() + item.getQuantity();
            if (result < 10000) {
                bindingResult.addError(new ObjectError("item", "아이템 가격 * 수량이 10,000 이상이어야 합니다. 현재 가격 = " + result));
            }
        }

        //오류가 있을 경우 현재 페이지에 남는다.
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "domain/addItem";
        }

        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/item/{itemId}";
    }

    @GetMapping("/item/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "domain/editItem";
    }

    @PostMapping("/item/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(item);
        return "redirect:/item/{itemId}";
    }
}
