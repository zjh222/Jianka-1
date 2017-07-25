package tech.jianka.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tech.jianka.activity.R;

/**
 * Created by Richa on 2017/7/25.
 */

public class CenterFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static CenterFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE,page);
        CenterFragment centerFragment = new CenterFragment();
        centerFragment.setArguments(args);
        return centerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_center, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #"+mPage);
        return textView;
    }
}
