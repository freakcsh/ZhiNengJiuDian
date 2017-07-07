package com.androidjob.zhinengjiudian.kefang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidjob.zhinengjiudian.MainActivity;
import com.androidjob.zhinengjiudian.R;

/**
 * Created by thinkpad on 2016/8/3.
 */

public class KeFangActivity extends Activity{
    //标题返回刷新按钮
    private Button fan_hui,s_xin;
    //电视小边框控件
    private ImageView kef_img_tv,zt_tv;
    //空调
    private TextView tv_kt,xian_shi_wen_du,xian_shi_sd;
    //窗帘
    private ImageView kef_img_curtain,img_cl_zt;
    //排气扇
    private ImageView kef_img_fan,fan_zt;
    //灯泡
    private ImageView kef_img_dp,zt_deng;
    //灯具
    private TextView fw_ctd,fw_zld,fw_ldd,fw_fdd,fw_bd;
    private ImageView zt_ctd,zt_zld,zt_ldd,zt_fdd,zt_bd;

   /* private  TextView img_curtain,img_zou, img_top,img_bed,
            img_floor,img_bi,img_tv_on,img_air,img_wc_fan_light;
    private TextView tv_curtain,tv_zou_lang,tv_top,
            tv_bed,tv_floor,tv_bi,tv_tv,tv_air,tv_wc_fan_light;
    private Button fanhui,shuaxin;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kefangguanli);
        chuShiHua();
        lightOnClick();
    }
    public void chuShiHua(){
        //图片TextView初始化
       /* img_curtain=(TextView)findViewById(R.id.img_curtain);
        img_zou=(TextView)findViewById(R.id.img_zou);
        img_top=(TextView)findViewById(R.id.img_top);
        img_bed=(TextView)findViewById(R.id.img_bed);
        img_floor=(TextView)findViewById(R.id.img_floor);
        img_bi=(TextView)findViewById(R.id.img_bi);
        img_tv_on=(TextView)findViewById(R.id.img_tv_on);
        img_air=(TextView)findViewById(R.id.img_air);
        img_wc_fan_light=(TextView)findViewById(R.id.img_wc_fan_light);*/
        //点击控件初始化
        //电视
        kef_img_tv=(ImageView)findViewById(R.id.kef_img_tv);
        //空调
        tv_kt=(TextView)findViewById(R.id.tv_kt);
        //窗帘
        kef_img_curtain=(ImageView)findViewById(R.id.kef_img_curtain);
        //排气扇
        kef_img_fan=(ImageView)findViewById(R.id.kef_img_fan);
        //灯泡
        kef_img_dp=(ImageView)findViewById(R.id.kef_img_dp);
        //床头灯
        fw_ctd=(TextView)findViewById(R.id.fw_ctd);
        //走廊灯
        fw_zld=(TextView)findViewById(R.id.fw_zld);
        //落地灯
        fw_ldd=(TextView)findViewById(R.id.fw_ldd);
        //房顶灯
        fw_fdd=(TextView)findViewById(R.id.fw_fdd);
        //壁灯
        fw_bd=(TextView)findViewById(R.id.fw_bd);


        //状态显示初始化
       /* tv_curtain=(TextView)findViewById(R.id.tv_curtain);
        tv_zou_lang=(TextView)findViewById(R.id.tv_zou_lang);
        tv_top=(TextView)findViewById(R.id.tv_top);
        tv_bed=(TextView)findViewById(R.id.tv_bed);
        tv_floor=(TextView)findViewById(R.id.tv_floor);
        tv_bi=(TextView)findViewById(R.id.tv_bi);
        tv_tv=(TextView)findViewById(R.id.tv_tv);
        tv_air=(TextView)findViewById(R.id.tv_air);
        tv_wc_fan_light=(TextView)findViewById(R.id.tv_wc_fan_light);*/
        //电视状态
        zt_tv=(ImageView) findViewById(R.id.zt_tv);
        //温度状态初始化
        xian_shi_wen_du=(TextView)findViewById(R.id.xian_shi_wen_du);
        //湿度状态初始化
        xian_shi_sd=(TextView)findViewById(R.id.xian_shi_sd);
        //窗帘状态初始化
        img_cl_zt=(ImageView)findViewById(R.id.img_cl_zt);
        //排气扇状态初始化
        fan_zt=(ImageView)findViewById(R.id.fan_zt);
        //灯泡状态初始化
        zt_deng=(ImageView)findViewById(R.id.zt_deng);
        //床头灯状态初始化
        zt_ctd=(ImageView)findViewById(R.id.zt_ctd);
        //走廊灯状态初始化
        zt_zld=(ImageView)findViewById(R.id.zt_zld);
        //落地灯状态初始化
        zt_ldd=(ImageView)findViewById(R.id.zt_ldd);
        //房顶灯状态初始化
        zt_fdd=(ImageView)findViewById(R.id.zt_fdd);
        //壁灯状态初始化
        zt_bd=(ImageView)findViewById(R.id.zt_bd);
        //返回刷新按钮初始化
        fan_hui=(Button)findViewById(R.id.fan_hui);
        s_xin=(Button)findViewById(R.id.s_xin);
    }
    public void lightOnClick(){
        fw_zld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent zouLang=new Intent();
                zouLang.setClass(KeFangActivity.this,LightActivity.class);
                startActivity(zouLang);
            }
        });
        kef_img_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wc=new Intent();
                wc.setClass(KeFangActivity.this,WCActivity.class);
                startActivity(wc);
            }
        });

        fw_fdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent top=new Intent();
                top.setClass(KeFangActivity.this,LightActivity.class);
                startActivity(top);
            }
        });

        fw_ctd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bed=new Intent();
                bed.setClass(KeFangActivity.this,LightActivity.class);
                startActivity(bed);
            }
        });
        fw_ldd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent floor=new Intent();
                floor.setClass(KeFangActivity.this,LightActivity.class);
                startActivity(floor);
            }
        });
        fw_bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bi=new Intent();
                bi.setClass(KeFangActivity.this,LightActivity.class);
                startActivity(bi);
            }
        });

        kef_img_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itv=new Intent();
                itv.setClass(KeFangActivity.this,DianQiActivity.class);
                startActivity(itv);
            }
        });

        kef_img_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dPao=new Intent();
                dPao.setClass(KeFangActivity.this,WCActivity.class);
                startActivity(dPao);
            }
        });
        kef_img_curtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent iCurtain=new Intent();
                iCurtain.setClass(KeFangActivity.this,CurtainActivity.class);
                startActivity(iCurtain);
            }
        });
        tv_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iAir=new Intent();
                iAir.setClass(KeFangActivity.this,DianQiActivity.class);
                startActivity(iAir);
            }
        });
        fan_hui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        s_xin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iShuaXin=new Intent();
                iShuaXin.setClass(KeFangActivity.this,KeFangActivity.class);
                startActivity(iShuaXin);
            }
        });
    }

    //页面跳转返回数据
    /*
    *通过startActivitForresult
    * requestCode请求的标识
    * resultCode：第二个页面返回的标识
    * data：第二个页面回传的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
