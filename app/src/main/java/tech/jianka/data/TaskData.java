package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.CardUtil.getChildItems;
import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

/**
 * Created by Richard on 2017/8/3.
 */

public class TaskData {
    private String[] taskGroup = {"重要|紧急", "重要|不紧急", "不重要|紧急", "不重要|不紧急"};

    private String[] paths = {
            "jianka/task/很重要-很紧急", "jianka/task/很重要-不紧急",
            "jianka/task/不重要-很紧急", "jianka/task/不紧急-不重要"};

    private List<Item> taskGroups = new ArrayList<>();
    private List<Item> taskItems = new ArrayList<>();

    public List<Item> getTaskItems() {
        return taskItems;
    }

    public TaskData() {
        //taskGroup初始化
        for (String group : taskGroup) {
            taskGroups.add(new Item(group, getSpecifiedSDPath("jianka/task")));
        }
        //taskItems初始化
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
