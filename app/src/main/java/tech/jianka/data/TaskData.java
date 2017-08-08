package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.ItemUtils.getSDCardPath;
import static tech.jianka.utils.ItemUtils.getTaskItems;

/**
 * Created by Richard on 2017/8/3.
 */

public class TaskData {
    private String[] taskGroup = {"重要|紧急", "重要|不紧急", "不重要|紧急", "不重要|不紧急"};

    private String[] paths = {
            "jianka/task/很重要-很紧急", "jianka/task/很重要-不紧急",
            "jianka/task/不重要-很紧急", "jianka/task/不紧急-不重要"};
    private List<Task> taskItems = new ArrayList<>();


    public TaskData() {
        //taskGroup初始化
        int[] type = DataType.TASK_TYPE;
        for (int i = 0; i < 4; i++) {
            taskItems.add(new Task(taskGroup[i], getSDCardPath("jianka/task"), DataType.GROUP, type[i]));
        }
        //taskItems初始化
        for (String path : paths) {
            List<Task> tasks = getTaskItems(getSDCardPath(path));
            if (tasks != null) {
                taskItems.addAll(tasks);
            }
        }
    }

    public List<Task> getTaskGroup() {
        return taskItems;
    }
}
