package tech.jianka.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static tech.jianka.utils.CardUtil.getGroupChildItems;
import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

/**
 * Created by Richard on 2017/8/3.
 */

public class GroupData {
    public static final int INBOX = 456;
    public static final int NOT_EMPTY = 413;
    public static final int DELETE_DONE = 783;
    private List<Item> itemGroup = new ArrayList<>();
    public GroupData() {
        String path = getSpecifiedSDPath("jianka/data");
        itemGroup = getGroupChildItems(path);
        Collections.sort(itemGroup, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if(o1.getFileName()=="收信箱"){
                    return 1;
                } else if (o2.getFileName() == "收信箱") {
                    return -1;
                } else if (o1.getModifiedTime() > o2.getModifiedTime()) {
                    return 1;
                }else {
                    return -1;
                }
            }
        });
    }

    public List<Item> getGroup() {
        return itemGroup;
    }
}
