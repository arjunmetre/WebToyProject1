package WebToyProject1.webService.controller;

import WebToyProject1.webService.domain.Item;
import WebToyProject1.webService.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/")
public class MainController {

    ItemRepository itemRepository;

    @Autowired
    public MainController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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
}
