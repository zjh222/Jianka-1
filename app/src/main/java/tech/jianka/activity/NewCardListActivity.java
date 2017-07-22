package tech.jianka.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NewCardListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card_list);

        setTitle(R.string.action_new_card_list);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
