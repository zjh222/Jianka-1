package tech.jianka.data;

import java.util.ArrayList;
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
    }

    public List<Item> getGroup() {
        return itemGroup;
    }
}
