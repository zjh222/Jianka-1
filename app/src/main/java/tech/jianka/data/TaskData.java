package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.CardUtil.getChildItems;
import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

/**
 * Created by Richa on 2017/8/3.
 */

public class TaskData {
    String[] taskGroup = {"重要|紧急", "重要|不紧急", "不重要|紧急", "不重要|不紧急"};

    String[] paths = {
            "jianka/task/important_emergent", "jianka/task/important_not_emergent",
            "jianka/task/unimportant_emergent", "jianka/task/unimportant_not_emergent"};


    private List<Item> taskGroups = new ArrayList<>();
    private List<Item> taskItems = new ArrayList<>();

    public List<Item> getTaskItems() {
        return taskItems;
    }

    public TaskData() {
        for (String group : taskGroup) {
            taskGroups.add(new Item(group, Item.GROUP));
        }
        for (String path : paths) {
            List<Item> items = getChildItems(getSpecifiedSDPath(path));
            if (items != null) {
                taskItems.addAll(items);
            }
        }
    }

    public List<Item> getTaskGroup() {
        return taskGroups;
    }
}
