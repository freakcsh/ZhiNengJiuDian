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
import android.widget.Button;

import com.androidjob.zhinengjiudian.kefang.KeFangActivity;
import com.androidjob.zhinengjiudian.other.QiTaFuWu;
import com.androidjob.zhinengjiudian.escape.Escape;
import com.androidjob.zhinengjiudian.yikatong.DoorActivity;

public class FunctionTab extends Fragment
{
  private Button keFangGuanLi;
    private Button yiKaTong;
    private Button qiTa;
    private Button canTing;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
      View view2=inflater.inflate(R.layout.gongneng,container,false);
        return view2;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        keFangGuanLi =(Button)getView().findViewById(R.id.gn_img_kef);
        yiKaTong=(Button)getView().findViewById(R.id.gn_img_k);
        qiTa=(Button)getView().findViewById(R.id.gn_fu_wu);
        canTing=(Button)getView().findViewById(R.id.gn_img_ts);
        keFangGuanLi.setOnClickListener(keFangOnClick);
        yiKaTong.setOnClickListener(yiKaTongOnClick);
        qiTa.setOnClickListener(qiTaOnClick);
        canTing.setOnClickListener(canTingOnClick);
    }
    private View.OnClickListener keFangOnClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent keFang=new Intent();
            keFang.setClass(getActivity(), KeFangActivity.class);
            startActivity(keFang);
        }
    };
    private View.OnClickListener yiKaTongOnClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent yiKaTong=new Intent();
            yiKaTong.setClass(getActivity(), DoorActivity.class);
            startActivity(yiKaTong);
        }
    };

    private View.OnClickListener qiTaOnClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
        Intent qiTa=new Intent();
            qiTa.setClass(getContext(), QiTaFuWu.class);
            startActivity(qiTa);
        }
    };

    private View.OnClickListener canTingOnClick=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
        Intent canTing=new Intent();
            canTing.setClass(getContext(), Escape.class);
            startActivity(canTing);
        }
    };


}
