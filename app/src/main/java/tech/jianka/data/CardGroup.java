package tech.jianka.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Richa on 2017/7/23.
 */

public class CardGroup extends ArrayList<Card> {
    private String groupTitle;
    private int isCard = 0;
    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    private int cardCount;
    public void newCard(int num) {
        for (int i = 0; i < num; i++) {
            this.add(new Card("card"+i,"richard",new Date(),"hello,world!", DataType.PICTURE_TXT));
        }
    }

    public CardGroup(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public int getType() {
        return isCard;
    }
}
