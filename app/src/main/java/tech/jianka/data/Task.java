package tech.jianka.data;

import android.support.annotation.Nullable;

/**
 * Created by Richa on 2017/8/8.
 */

public class Task extends Item {
    private String taskTitle;
    private String taskContent;
    private int taskType;

    public Task(String taskTitle,String filePath, @Nullable String taskContent, int taskType) {
        super.setFilePath(filePath);
        this.taskTitle = taskTitle;
        this.taskContent = taskContent;
        this.taskType = taskType;
    }

    /**
     * 为列表生成的构造函数
     * @param fileName
     * @param filePath
     * @param itemType
     * @param taskType
     */
    public Task(String fileName,String filePath, int itemType,int taskType) {
        super(fileName,filePath,itemType);
        this.taskType = taskType;
    }

    public Task() {

    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }
}
