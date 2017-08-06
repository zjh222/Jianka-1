package tech.jianka.fragment;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richa on 2017/8/5.
 */

public class FragmentManager {
    public static final int GROUP_FRAGMENT = 0;
    public static final int RECENT_FRAGMENT = 1;
    public static final int TASK_FRAGMENT = 2;
    public static List<Fragment> fragmentList = new ArrayList<>();

    public static GroupFragment getGroupFragment() {
        GroupFragment fragment =(GroupFragment) fragmentList.get(GROUP_FRAGMENT);
        return fragment;
    }

    public static RecentFragment getRecentFragment() {
        RecentFragment fragment = (RecentFragment) fragmentList.get(RECENT_FRAGMENT);
        return fragment;
    }

    public static TaskFragment getTaskFragment() {
        TaskFragment fragment = (TaskFragment) fragmentList.get(TASK_FRAGMENT);
        return fragment;
    }

    public static Fragment getFragment(int index) {
        if (index == GROUP_FRAGMENT) {
            GroupFragment fragment =(GroupFragment) fragmentList.get(index);
            return fragment;
        } else if (index == RECENT_FRAGMENT) {
            RecentFragment fragment = (RecentFragment) fragmentList.get(index);
            return fragment;
        } else {
            TaskFragment fragment = (TaskFragment) fragmentList.get(index);
            return fragment;
        }
    }

}
