package tech.jianka.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import tech.jianka.adapter.CardAdapter;
import tech.jianka.data.CardArray;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,CardAdapter.CardClickListener{
    private CardAdapter mAdapter;
    private RecyclerView mCardRecycle;
    private Toast mToast;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final Context context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NewCardActivity.class);
                startActivity(intent);
            }
        });

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mCardRecycle = (RecyclerView) findViewById(R.id.main_recycler_view);
        mCardRecycle.setHasFixedSize(true);
        CardArray cardArray = new CardArray();
        cardArray.newCard(50);
        mAdapter = new CardAdapter(cardArray,this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        layoutManager.setOrientation(GridLayout.VERTICAL);
        mCardRecycle.setAdapter(mAdapter);
        mCardRecycle.setLayoutManager(layoutManager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            navSettings();
            return true;
        }else if (id == R.id.action_new_card_list){
            navCreateCardList();
            return true;
        } else if (id == R.id.action_show_hidden) {
            showHiddenCardList();
            return true;
        } else if (id == R.id.action_search) {
            searchCard();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void searchCard() {
        // TODO: 2017/7/22
    }

    private void showHiddenCardList() {
        // TODO: 2017/7/22
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_friend:
                navFriendList();
                break;
            case R.id.nav_message:
                navMessage();
                break;
            case R.id.nav_arrange:
                navArrange();
                break;
            case R.id.nav_create_cardlist:
                navCreateCardList();
                break;
            case R.id.nav_add_folder:
                navAddFolder();
                break;
            case R.id.nav_recyclebin:
                navRecycleBin();
                break;
            case R.id.nav_settings:
                navSettings();

                break;
            case R.id.nav_share:
                navShare();
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navShare() {

    }

    private void navSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void navRecycleBin() {

    }

    private void navAddFolder() {

    }


    private void navCreateCardList() {
        startActivity(new Intent(this,NewCardListActivity.class));
    }

    private void navArrange() {

    }

    private void navMessage() {
        startActivity(new Intent(this, MessageActivity.class));
    }

    private void navFriendList() {
        startActivity(new Intent(this,FriendListActivity.class));
    }

    @Override
    public void onCardClick(int clickedCardIndex) {
        if(mToast != null){
            mToast.cancel();
        }
        String toastMessage = "Card" +clickedCardIndex +"  clicked";
        mToast = Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT);
        mToast.show();
    }
}
