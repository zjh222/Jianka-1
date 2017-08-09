package tech.jianka.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.ItemUtils.getAllGroups;
import static tech.jianka.utils.ItemUtils.getSDCardPath;

/**
 * Created by Richard on 2017/8/3.
 */

public class GroupData {
    public static final int INBOX = 456;
    public static final int NOT_EMPTY = 413;
    public static final int DELETE_DONE = 783;
    private static List<Group> groupItems;
    private static List<String> groupTitles = new ArrayList<>();

    public GroupData() {
        groupItems = getAllGroups(getSDCardPath("jianka/data"));
    }

    public static List<String> getGroupTitles() {
        for (Group group : groupItems) {
            groupTitles.add(group.getFileName());
        }
        groupTitles.add(1, "任务");
        return groupTitles;
    }

    public static List<Group> getGroup() {
        return groupItems;
    }

    public static void addGroup(Group group) {

        new File(group.getFilePath()).mkdirs();
    }

    public static int removeGroup(Group group) {
        if (group.getFileName().equals("收信箱")) {
            return GroupData.INBOX;
        }
        File file = new File(group.getFilePath());
        if (file.list() != null && file.list().length > 0) {
            return GroupData.NOT_EMPTY;
        } else {
            file.delete();
            return GroupData.DELETE_DONE;
        }
    }

    public static void removeGroupAndCards(Group group) {
        new File(group.getFilePath()).delete();
        new File(group.getCoverPath()).delete();
    }

    public static void renameGroup(int index, String newName) {
        Group group = groupItems.get(index);
        group.setFileName(newName);
        File file = new File(groupItems.get(index).getFilePath());
        file.renameTo(new File(file.getParent() + "/" + newName));
    }
}
