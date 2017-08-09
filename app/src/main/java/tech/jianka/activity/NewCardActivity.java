package tech.jianka.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tech.jianka.adapter.MyAdapter;
import tech.jianka.data.Item;
import tech.jianka.fragment.FragmentManager;

import static tech.jianka.utils.CardUtil.getGroupChildItems;
import static tech.jianka.utils.CardUtil.getSpecifiedSDPath;

public class NewCardActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mTaskSelecotor;
    private EditText mEditTitle;
    private Spinner mGroupSelector;
    private EditText mEditContent;
    private TextView mIndicator;
    private ImageView iv_image;
    private Item item;
    private String str;
    private DBConnection helper;

    private BaseAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        this.iv_image = (ImageView) findViewById(R.id.iv_image);//li

        setTitle(getString(R.string.new_card_activity));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mIndicator = (TextView) findViewById(R.id.new_card_task_indicator);
        mEditTitle = (EditText) findViewById(R.id.new_card_title);
        mEditContent = (EditText) findViewById(R.id.new_card_content);
        mTaskSelecotor = (RadioGroup) findViewById(R.id.new_card_task_selector);
        mGroupSelector = (Spinner) findViewById(R.id.new_card_group_selector);
        mEditContent = (EditText) findViewById(R.id.new_card_content);
        mTaskSelecotor.setOnCheckedChangeListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            item =(Item) bundle.getSerializable("CARD_DETAIL");
        }

        ArrayList<String> groupItem = new ArrayList<>();
        List<Item> items = getGroupChildItems(getSpecifiedSDPath("jianka/data"));
        items.add(new Item("任务", getSpecifiedSDPath("jianka/task")));
        for (Item item : items) {
            groupItem.add(item.getFileName());
        }

        myAdapter = new MyAdapter<String>(groupItem, R.layout.spinner_item) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.spinner_item_text_view, obj);
            }
        };

        mGroupSelector.setAdapter(myAdapter);

        helper = new DBConnection(this);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        str = formatter.format(curDate);
        if (!checkNetworkInfo()) {
            return;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            // 得到图片的全路径
            Uri uri = data.getData();
            // 通过路径加载图片
            //这里省去了图片缩放操作，如果图片过大，可能会导致内存泄漏
            //图片缩放的实现，请看：http://blog.csdn.net/reality_jie_blog/article/details/16891095
            this.iv_image.setImageURI(uri);
            // 获取图片的缩略图，可能为空！
            // Bitmap bitmap = data.getParcelableExtra("data");
            // this.iv_image.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    //2017/8/4

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_save:
                saveCard();
                finish();
                return true;
            case R.id.action_share:
                //li2017/8/4
                if (isEmpty()) {
                    ContentValues values = new ContentValues();
                    values.put("content",
                            Html.fromHtml(mEditContent.getText().toString()) + "");
                    values.put("writetime", str);

//                    SQLiteDatabase db = helper.getWritableDatabase();
//                    db.insert("content",null,values);
//                    db.close();
//
//                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(btnRight.getWindowToken(),0);

                    isShare();
                }
                return true;
            case R.id.action_insert_image:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //li2
    private void isShare() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_menu_share)
                .setTitle("发表成功，立即分享？")
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                        intent.putExtra(Intent.EXTRA_TEXT, str + "\n" + mEditContent.getText().toString());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(Intent.createChooser(intent, getTitle()));

                        mEditContent.setText("");
                    }
                }).setNegativeButton("以后", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mEditContent.setText("");
            }
        }).show();
    }

    public boolean checkNetworkInfo() {

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // mobile 3G Data Network
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        // wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        // 如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接

        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("需要网络支持！")
                .setCancelable(false)
                .setPositiveButton("进行网络配置",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // 进入无线网络配置界面
                                startActivity(new Intent(
                                        Settings.ACTION_WIRELESS_SETTINGS));
                                NewCardActivity.this.finish();
                            }
                        })
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewCardActivity.this.finish();
                    }
                });
        builder.show();
        return false;

    }

    private boolean isEmpty() {
        if (mEditContent.getText() == null
                || mEditContent.getText().toString().trim().equals("")) {
            Toast toast = Toast.makeText(NewCardActivity.this,
                    this.getText(R.string.content_null), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }
        return true;
    }

    private void saveCard() {
        String title = mEditTitle.getText().toString();
        String content = mEditContent.getText().toString();
        String filePath;
        Item newCard;
        if (mIndicator.getText().toString().equals("普通卡片")) {
            filePath = "jianka/data/" + mGroupSelector.getSelectedItem().toString();
            newCard =  new Item(title, Item.CARD, filePath, content);
            FragmentManager.getRecentFragment().adapter.addItem(newCard);

        } else {
            filePath = "jianka/data/" + mIndicator.getText().toString();
            newCard =  new Item(title, Item.CARD, filePath, content);
            FragmentManager.getTaskFragment().adapter.addItem(newCard);
        }
        // TODO: 2017/8/6 分两种情况
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group.getId() == R.id.new_card_task_selector) {
            String[] tasks = getResources().getStringArray(R.array.task);
            switch (checkedId) {
                case R.id.task_regular:
                    mIndicator.setText(tasks[0]);
                    break;
                case R.id.task_important_emergent:
                    // TODO: 2017/8/6 设置和spinner的联动
                    mIndicator.setText(tasks[1]);
                    break;
                case R.id.task_important_not_emergent:
                    mIndicator.setText(tasks[2]);
                    break;
                case R.id.task_unimportant_emergent:
                    mIndicator.setText(tasks[3]);
                    break;
                case R.id.task_unimportant_not_emergent:
                    mIndicator.setText(tasks[4]);
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