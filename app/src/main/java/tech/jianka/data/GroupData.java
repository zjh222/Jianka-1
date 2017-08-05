package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.CardUtil.getChildItems;
import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

/**
 * Created by Richard on 2017/8/3.
 */

public class GroupData {
    private List<Item> itemGroup = new ArrayList<>();

    public GroupData() {
        String path = getSpecifiedSDPath("jianka/data");
        itemGroup = getChildItems(path);
        itemGroup.add(new Item("任务",getSpecifiedSDPath("jianka/task")));
    }

    public List<Item> getItemGroup() {
        return itemGroup;
    }
}
