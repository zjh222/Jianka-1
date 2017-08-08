package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.ItemUtils.getSDCardPath;
import static tech.jianka.utils.ItemUtils.getSubGroups;

/**
 * Created by Richard on 2017/8/3.
 */

public class GroupData {
    public static final int INBOX = 456;
    public static final int NOT_EMPTY = 413;
    public static final int DELETE_DONE = 783;
    private List<Group> groupItems;
    private static List<String> groupTitles = new ArrayList<>();

    public GroupData() {
        groupItems = getSubGroups(getSDCardPath("jianka/data"));
        for (Group group : groupItems) {
            groupTitles.add(group.getFileName());
        }
    }

    public static List<String> getGroupTitles() {
        return groupTitles;
    }

    public List<Group> getGroup() {
        return groupItems;
    }
}
