package WebToyProject1.webService.controller;

import WebToyProject1.webService.domain.Item;
import WebToyProject1.webService.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final ItemRepository itemRepository;

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
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
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
