package tech.jianka.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Richard on 2017/7/25.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<Fragment> fragmentList;

    public HomeFragmentPagerAdapter(FragmentManager manager, String[] titles, List<Fragment> fragmentList) {
        super(manager);
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