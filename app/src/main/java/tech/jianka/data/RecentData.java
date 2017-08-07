package tech.jianka.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;
import static tech.jianka.utils.CardUtil.inflateCardFromPath;
import static tech.jianka.utils.SDCardHelper.Obj2Bytes;
import static tech.jianka.utils.SDCardHelper.saveFileToSDCard;

/**
 * Created by Richa on 2017/8/3.
 */

public class RecentData {
    // TODO: 2017/8/3 读取最近修改的卡片数据
    private List<Item> data = new ArrayList<>();

    public RecentData() {
        ArrayList<String> recentList = getRecentList();
        if (recentList != null) {
            for (String filePath : getRecentList()) {
                data.add(inflateCardFromPath(filePath));
            }
        }
    }

    public List<Item> getData() {
        return data;
    }

    public class RecentList extends ArrayList<String> implements Serializable {
        ArrayList<String> recentList = new ArrayList<>();

        public RecentList(ArrayList<String> recentList) {
            this.recentList = recentList;
        }
    }
    public static ArrayList<String> getRecentList() {
        File file = new File(getSpecifiedSDPath("jianka/log/recent.list"));
        FileInputStream fis;
        ObjectInputStream ois;
        RecentList list;
        try {
            fis = new FileInputStream(file.toString());
            ois = new ObjectInputStream(fis);
            list = (RecentList) ois.readObject();
            return list.recentList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public  void addItem(String filePath){
        RecentList recentList = new RecentList(getRecentList());
        recentList.recentList.add(filePath);
        // TODO: 2017/8/7 检查是否是覆盖
        saveFileToSDCard(Obj2Bytes(recentList),"jianka/log", "recent.list");

    }
}
