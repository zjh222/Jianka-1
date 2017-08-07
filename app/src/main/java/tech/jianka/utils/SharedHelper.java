package tech.jianka.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import tech.jianka.activity.R;

/**
 * Created by Richa on 2017/7/29.
 */

public class SharedHelper {

    private Context mContext;

    Context context;

    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
    }
//
//    public void save(String arrayName,String item){
//        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor
//    }
    //定义一个保存数据的方法
    public void save(String username, String passwd) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        Toast.makeText(mContext,context.getResources().getString(R.string.message_write_successful) , Toast.LENGTH_SHORT).show();
    }

    //定义一个读取SP文件的方法
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }
}