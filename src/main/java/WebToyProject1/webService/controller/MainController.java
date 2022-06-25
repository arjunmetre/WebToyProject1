package WebToyProject1.webService.controller;

import WebToyProject1.webService.domain.DeliveryCode;
import WebToyProject1.webService.domain.Item;
import WebToyProject1.webService.domain.ItemType;
import WebToyProject1.webService.domain.form.ItemEditForm;
import WebToyProject1.webService.domain.form.ItemSaveForm;
import WebToyProject1.webService.repository.ItemRepository;
import WebToyProject1.webService.validation.ItemValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //오류가 있을 경우 현재 페이지에 남는다.
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "domain/addItem";
        }

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setOpen(form.getOpen());
        item.setRegions(form.getRegions());
        item.setItemType(form.getItemType());
        item.setDeliveryCode(form.getDeliveryCode());


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
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemEditForm form, BindingResult bindingResult) {

        //오류가 있을 경우 현재 페이지에 남는다.
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult);
            return "domain/editItem";
        }

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setOpen(form.getOpen());
        item.setRegions(form.getRegions());
        item.setItemType(form.getItemType());
        item.setDeliveryCode(form.getDeliveryCode());

        itemRepository.update(itemId, item);
        return "redirect:/item/{itemId}";
    }
}
