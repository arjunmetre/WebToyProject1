package WebToyProject1.webService.domain.item;

import lombok.Getter;

@Getter
public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private String description;

    ItemType(String description) {
        this.description = description;
    }
}
