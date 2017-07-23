package tech.jianka.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Richa on 2017/7/23.
 */

public class CardArray extends ArrayList<Card> {
    public void newCard(int num) {
        for (int i = 0; i < num; i++) {
            this.add(new Card("card"+i,"richard",new Date(),"hello,world!",CardType.GENERAL));
        }
    }
}
