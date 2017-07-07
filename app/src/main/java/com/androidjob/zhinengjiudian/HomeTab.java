package com.androidjob.zhinengjiudian;

/**
 * Created by thinkpad on 2016/7/22.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeTab extends Fragment
{




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //初始化对应的view并返回
        View view=inflater.inflate(R.layout.shouye,container,false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化对应的vie里面的控件，并为对应的控件设置监听器




    }


}
