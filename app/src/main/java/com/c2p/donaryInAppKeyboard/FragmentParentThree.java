package com.c2p.donaryInAppKeyboard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c2p.customviews.VerticalViewPager;
import com.c2p.donaryInAppKeyboards.R;

public class FragmentParentThree extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";

    private int mNum;
    private int mColor;

    static final int NUMBER_OF_PAGES = 2;
    VerticalViewPager mPager;
    MyAdapter2 mAdapter;

    // You can modify the parameters to pass in whatever you want
    static FragmentParentThree newInstance(int num, int color) {
        FragmentParentThree f = new FragmentParentThree();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_parent_three, container, false);
        //v.setBackgroundColor(mColor);
        //TextView textView = v.findViewById(R.id.tv);
        //textView.setText("Outer Pager: " + mNum);

        mAdapter = new MyAdapter2(getChildFragmentManager()); //**
        mPager = v.findViewById(R.id.hzPager);
        mPager.setAdapter(mAdapter);

        return v;
    }


    public static class MyAdapter2 extends FragmentPagerAdapter {

        public MyAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new HebrewAbcKeybardFragment();
                case 1:
                    return QuertyBigkeyHebrewKbdFragment.newInstance(1,"0");
                default:
                    return null;
            }
        }
    }
}
