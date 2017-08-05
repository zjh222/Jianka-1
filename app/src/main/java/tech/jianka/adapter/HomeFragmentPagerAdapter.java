package tech.jianka.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import tech.jianka.fragment.TabsFragment;

/**
 * Created by Richard on 2017/7/25.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private List<TabsFragment> fragmentList;

    public HomeFragmentPagerAdapter(FragmentManager manager, String[] titles, List<TabsFragment> fragmentList) {
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