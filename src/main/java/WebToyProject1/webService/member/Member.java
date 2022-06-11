package WebToyProject1.webService.member;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class Member {

    private long id;
    private long drawCount;
    private ArrayList<Object> message;

    public Member(int id, int drawCount, ArrayList<Object> message) {
        this.id = id;
        this.drawCount = drawCount;
        this.message = message;
    }
}
