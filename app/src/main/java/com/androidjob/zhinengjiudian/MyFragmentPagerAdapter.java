package com.androidjob.zhinengjiudian;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by thinkpad on 2016/7/29.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>mTabs;
   public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment>mTabs){
    super(fm);
       this.mTabs=mTabs;
    }
    @Override
    public int getCount()
    {
        return mTabs.size();
    }


    @Override
    public Fragment getItem(int position)
    {
        return mTabs.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
