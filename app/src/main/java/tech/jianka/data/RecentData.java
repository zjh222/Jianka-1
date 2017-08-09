package tech.jianka.data;

import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.ItemUtils.getAllSubCards;
import static tech.jianka.utils.ItemUtils.getSDCardPath;

/**
 * Created by Richa on 2017/8/3.
 */

public class RecentData {
    // TODO: 2017/8/3 读取最近修改的卡片数据
    private static List<Card> data = new ArrayList<>();

    public RecentData() {
        data = getAllSubCards(getSDCardPath("jianka/data"));
    }

    public List<Card> getData() {
        return data;
    }


}
