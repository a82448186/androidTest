package com.example.tf018145.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ViewPagerActivity extends AppCompatActivity {
    ViewPager viewPager;

    View view1;
    View view2;
    View view3;
    View view4;

    PagerTabStrip pagerTabStrip;

    List<View> viewList = new ArrayList<View>();
    List<String> titleList = new ArrayList<String>();
    List<Fragment> fragmentList = new ArrayList<Fragment>();
    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.w("销毁", "销毁第" + (position + 1) + "页");
            container.removeView(viewList.get(position));
        }
    };

    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        LayoutInflater layoutInflater = getLayoutInflater();

        view1 = layoutInflater.inflate(R.layout.view_page1, null);
        view2 = layoutInflater.inflate(R.layout.view_page2, null);
        view3 = layoutInflater.inflate(R.layout.view_page3, null);
        view4 = layoutInflater.inflate(R.layout.view_page4, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        String[] titles = {"第一页", "第二页", "第三页", "第四页"};
        titleList = Arrays.asList(titles);
        fragmentList.add(new ViewPageFragment1());
        fragmentList.add(new ViewPageFragment2());
        fragmentList.add(new ViewPageFragment3());
        fragmentList.add(new ViewPageFragment4());

//        viewPager.setAdapter(pagerAdapter);
        viewPager.setAdapter(fragmentPagerAdapter);


    }
}
