package tech.jianka.activity;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class NewCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        setTitle(getString(R.string.new_card_activity));
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
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