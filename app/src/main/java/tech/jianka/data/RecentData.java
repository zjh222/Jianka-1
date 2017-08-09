package tech.jianka.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;
import static tech.jianka.utils.CardUtil.inflateCardFromPath;

/**
 * Created by Richa on 2017/8/3.
 */

public class RecentData {
    // TODO: 2017/8/3 读取最近修改的卡片数据
    private List<Item> data = new ArrayList<>();

    public RecentData() {
        String path = getSpecifiedSDPath("jianka/log/recent.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path)));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                data.add(inflateCardFromPath(line));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Item> getData() {
        return data;
    }

}
