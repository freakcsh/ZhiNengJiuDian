package com.androidjob.zhinengjiudian;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by thinkpad on 2016/7/27.
 */
public class MypagerAdapter extends PagerAdapter{
    private List<View>viewList;
    private Button button;
    private TextView textView;
    public MypagerAdapter(List<View>viewList){
        this.viewList=viewList;

    }
    /*
    返回的是页卡的数量
     */
    @Override
    public int getCount() {
        return viewList.size();
    }
    /*
    view是否来自于对象
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
/*
实例化一个页卡
 */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        return viewList.get(position);
    }
/*
销毁一个页卡
 */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public void finishUpdate(ViewGroup container) {

    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
