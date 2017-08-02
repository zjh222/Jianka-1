package tech.jianka.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class NewCardActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mTaskSelecotor;
    private EditText mEditTitle;
    private Spinner mGroupSelector;
    private TextView mTextCreateDate;
    private EditText mEditContent;
    private TextView mTaskIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        setTitle(getString(R.string.new_card_activity));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mEditTitle = (EditText) findViewById(R.id.new_card_title);
        mEditContent = (EditText) findViewById(R.id.new_card_content);
        mTaskSelecotor = (RadioGroup) findViewById(R.id.new_card_task_selector);
        mGroupSelector = (Spinner) findViewById(R.id.new_card_group_selector);
        mEditContent = (EditText) findViewById(R.id.new_card_content);
        //mTextCreateDate = (TextView) findViewById(R.id.new_card_created_time);
        mTaskIndicator = (TextView) findViewById(R.id.new_card_task_indicator);

        mTaskSelecotor.setOnCheckedChangeListener(this);

        putString("group1","hello",this);
        mEditContent.append(getString("group1",this));
        if (getString("hello", this) != null) {
            mEditContent.append(getString("hello", this) );
        }else{
            mEditContent.append("没找到");
        }
        if (getString("inbox", this) != null) {
            mEditContent.append(getString("inbox", this) );
        }else{
            mEditContent.append("没找到");
        }

    }
    public static void putString(String key, String value, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("pref_group", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public static String getString(String key, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("pref_group", Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_card,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group.getId() == R.id.new_card_task_selector) {
            String[] tasks = getResources().getStringArray(R.array.task);
            TextView indicator = (TextView) findViewById(R.id.new_card_task_indicator);
            switch (checkedId) {
                case R.id.task_regular:
                    indicator.setText(tasks[0]);
                    break;
                case R.id.task_important_emergent:
                    indicator.setText(tasks[1]);
                    break;
                case R.id.task_important_not_emergent:
                    indicator.setText(tasks[2]);
                    break;
                case R.id.task_unimportant_emergent:
                    indicator.setText(tasks[3]);
                    break;
                case R.id.task_unimportant_not_emergent:
                    indicator.setText(tasks[4]);
                    break;
            }
        }
    }
//    StringBuffer status = new StringBuffer();
//    //①获取系统的Configuration对象
//    Configuration cfg = getResources().getConfiguration();
//    RecyclerViewBanner recyclerViewBanner1 = (RecyclerViewBanner) findViewById(R.id.rv_banner_1);
//
//    //②想查什么查什么
//        status.append("densityDpi:" + cfg.densityDpi + "\n");
//        status.append("fontScale:" + cfg.fontScale + "\n");
//        status.append("hardKeyboardHidden:" + cfg.hardKeyboardHidden + "\n");
//        status.append("keyboard:" + cfg.keyboard + "\n");
//        status.append("keyboardHidden:" + cfg.keyboardHidden + "\n");
//        status.append("locale:" + cfg.locale + "\n");
//        status.append("mcc:" + cfg.mcc + "\n");
//        status.append("mnc:" + cfg.mnc + "\n");
//        status.append("navigation:" + cfg.navigation + "\n");
//        status.append("navigationHidden:" + cfg.navigationHidden + "\n");
//        status.append("orientation:" + cfg.orientation + "\n");
//        status.append("screenHeightDp:" + cfg.screenHeightDp + "\n");
//        status.append("screenWidthDp:" + cfg.screenWidthDp + "\n");
//        status.append("screenLayout:" + cfg.screenLayout + "\n");
//        status.append("smallestScreenWidthDp:" + cfg.densityDpi + "\n");
//        status.append("touchscreen:" + cfg.densityDpi + "\n");
//        status.append("uiMode:" + cfg.densityDpi + "\n");
//        txtResult.setText(status.toString());

}