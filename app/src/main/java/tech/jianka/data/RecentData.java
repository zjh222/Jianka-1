package tech.jianka.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richa on 2017/8/3.
 */

public class RecentData {
    // TODO: 2017/8/3 读取最近修改的卡片数据
    private List<Card> data = new ArrayList<>();



    public List<Card> getData() {
        return data;
    }

    public class RecentList extends ArrayList<String> implements Serializable {
        ArrayList<String> recentList = new ArrayList<>();

        public RecentList(ArrayList<String> recentList) {
            this.recentList = recentList;
        }
    }

}
