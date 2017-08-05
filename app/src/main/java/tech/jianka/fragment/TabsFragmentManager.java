package tech.jianka.fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richa on 2017/8/5.
 */

public class TabsFragmentManager {
    public static final int GROUP_FRAGMENT = 0;
    public static final int RECENT_FRAGMENT = 1;
    public static final int TASK_FRAGMENT = 2;
    public static List<TabsFragment> fragmentList = new ArrayList<>();

    public static TabsFragment getFragment(int index) {
        return fragmentList.get(index);
    }

}
