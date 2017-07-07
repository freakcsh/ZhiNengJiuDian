package com.androidjob.zhinengjiudian.kefang;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.androidjob.zhinengjiudian.MyFragmentPagerAdapter;
import com.androidjob.zhinengjiudian.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2016/8/3.
 */
public class WCActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private ViewPager wJuViewPager;
    private ViewPagerIndicator wJuIndicator;
    private List<Fragment> wTab = new ArrayList<Fragment>();
    private FragmentPagerAdapter wFragmentPagerAdapter;
    private Button wc_fan_hui;


    private PrintWriter writer;
    private Socket socket = null;
    static String message;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wctab);
        connect();

        wc_fan_hui = (Button) findViewById(R.id.wc_fan_hui);
       /* lViewList=new ArrayList<View>();
        View lView1=View.inflate(this,R.layout.light_bed,null);
        View lView2=View.inflate(this,R.layout.light_zoulang,null);
        View lView3=View.inflate(this,R.layout.light_floor,null);
        View lView4=View.inflate(this,R.layout.light_top,null);
        View lView5=View.inflate(this,R.layout.light_bi,null);

        lViewList.add(lView1);
        lViewList.add(lView2);
        lViewList.add(lView3);
        lViewList.add(lView4);
        lViewList.add(lView5);*/


        wTab.add(new WCFanFragment());
        wTab.add(new WCLightFragment());
        wFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), wTab);
        wJuViewPager = (ViewPager) findViewById(R.id.wViewpager);
        wJuViewPager.setAdapter(wFragmentPagerAdapter);
        wJuViewPager.setOnPageChangeListener(this);
        wJuIndicator = (ViewPagerIndicator) findViewById(R.id.wIndicator);


        wc_fan_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //偏移量 tabWidth*positionOffset+position*tabWidth
        wJuIndicator.scroll(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public void connect() {
        AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... arg0) {
                socket = SocketOpen.getSocket();
                writer = SocketOpen.getSocketPrintWriter();
                inputStream = SocketOpen.getSocketInputStream();
                receive();
                publishProgress("@success");
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("@success")) {
                    Toast.makeText(WCActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                }
                super.onProgressUpdate(values);
            }
        };
        read.execute();

    }


    //接收服务器主动发来的消息
    public void receive() {
        AsyncTask<String, String, String> mes = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String res = null;
                try {
                    byte buffer[] = new byte[512];
                    int temp = 0;
                    //从inputstream中读取客户端所发送的数据
//                    System.out.println("接收到服务器的信息是：");
                    while ((temp = inputStream.read(buffer, 0, 10)) != -1) {
                        message = new String(buffer, 0, temp);
                        res = message;
//                        System.out.println(line);
                        publishProgress(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected void onProgressUpdate(String... values) {

                String serverMessage = values[0];
                if (serverMessage.equals("20111") || serverMessage.equals("21111")) {
                    sendToExhaustFan();
                } else if (serverMessage.equals("") || serverMessage.equals("")) {
                    sendToWCLight();
                }

                super.onProgressUpdate(values);
            }
        };
        mes.execute();
    }

    public void sendToExhaustFan() {
        WCFanFragment wcFanFragment = new WCFanFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        wcFanFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.wcfanlayout, wcFanFragment);
        beginTransaction.commit();
    }

    public void sendToWCLight() {
        WCLightFragment wcLightFragment = new WCLightFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        wcLightFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.wclightlayout, wcLightFragment);
        beginTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();

    }
}
