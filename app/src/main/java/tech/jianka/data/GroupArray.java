package tech.jianka.data;

import java.util.ArrayList;

/**
 * Created by Richa on 2017/7/27.
 */

public class GroupArray extends ArrayList<CardGroup> {
    public void addGroup(int addNumber){
        for(int i=0;i<addNumber;i++) {
            add(new CardGroup("hello title"));
        }
    }
}
