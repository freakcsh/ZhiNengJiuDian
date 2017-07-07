package com.androidjob.zhinengjiudian;

/**
 * Created by thinkpad on 2016/7/22.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidjob.zhinengjiudian.me.FanKuiActivity;
import com.androidjob.zhinengjiudian.me.GuanYu_Activitty;
import com.androidjob.zhinengjiudian.me.MyZhangHao_Activity;
import com.androidjob.zhinengjiudian.me.Setting_Activity;
import com.androidjob.zhinengjiudian.me.TuiChuDengLu_Activity;
import com.androidjob.zhinengjiudian.me.TuiJian_Activity;

public class MeTab extends Fragment
{
   private TextView txt_usersafe,setting,fenxiang,
           fankui,txt_about,btnexit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
      View view3=inflater.inflate(R.layout.wo,container,false);
        return view3;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txt_usersafe=(TextView)getView().findViewById(R.id.txt_usersafe);
        setting=(TextView)getView().findViewById(R.id.setting);
        fenxiang=(TextView)getView().findViewById(R.id.fenxiang);
        fankui=(TextView)getView().findViewById(R.id.fankui);
        txt_about=(TextView)getView().findViewById(R.id.txt_about);
        btnexit=(TextView)getView().findViewById(R.id.btnexit);

        txt_usersafe.setOnClickListener(txt_usersafeOnClick);
        setting.setOnClickListener(settingOnClick);
        fenxiang.setOnClickListener(fenxiangOnClick);
        fankui.setOnClickListener(fankuiOnclick);
        txt_about.setOnClickListener(txt_aboutOnClick);
        btnexit.setOnClickListener(btnexitOnClick);
    }
    private View.OnClickListener txt_usersafeOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent itxt_usersafe=new Intent();
            itxt_usersafe.setClass(getActivity(), MyZhangHao_Activity.class);
            startActivity(itxt_usersafe);
        }
    };

    private View.OnClickListener settingOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent isetting=new Intent();
            isetting.setClass(getActivity(), Setting_Activity.class);
            startActivity(isetting);
        }
    };

    private View.OnClickListener fenxiangOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Intent ifenxiang=new Intent();
            ifenxiang.setClass(getActivity(), TuiJian_Activity.class);
            startActivity(ifenxiang);
        }
    };

    private View.OnClickListener fankuiOnclick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent iFanKui=new Intent();
            iFanKui.setClass(getActivity(), FanKuiActivity.class);
            startActivity(iFanKui);
        }
    };

    private View.OnClickListener txt_aboutOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent iAbout=new Intent();
            iAbout.setClass(getActivity(), GuanYu_Activitty.class);
            startActivity(iAbout);
        }
    };

    private View.OnClickListener btnexitOnClick=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent iExit=new Intent();
            iExit.setClass(getActivity(), TuiChuDengLu_Activity.class);
            startActivity(iExit);
        }
    };
}
