package tech.jianka.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Richa on 2017/7/25.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragmentList;
    private CardAdapter.CardItemClickListener listener;

    public HomeFragmentPagerAdapter(FragmentManager manager, String[] titles, List<Fragment> fragmentList, CardAdapter.CardItemClickListener listener) {
        super(manager);
        this.listener = listener;
        this.titles = titles;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}