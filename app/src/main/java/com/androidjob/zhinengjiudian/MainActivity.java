package com.androidjob.zhinengjiudian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        ,ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private List<View>viewList;
    private FragmentPagerAdapter mAdapter;
    private List<ChangeColorIconWithText> mTabIndicators = new ArrayList<ChangeColorIconWithText>();

    private long lastClickTime=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        1.初始化viewList
        2.绑定对应的xml文件，并设置类型是view
        3.添加绑定的view到viewList
         */
        viewList=new ArrayList<View>();

        View view= View.inflate(this,R.layout.shouye,null);
        View view1= View.inflate(this,R.layout.qiantai,null);
        View view2= View.inflate(this,R.layout.gongneng,null);
        View view3= View.inflate(this,R.layout.wo,null);





        viewList.add(view);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        /*
        1.创建对应的Frangment文件，并绑定布局xml文件
        2.创建对应的frangment对象并加入到fragment类型的List集合mTabs中
         */
        mTabs.add(new HomeTab());
        mTabs.add(new ReceptionistTab());
        mTabs.add(new FunctionTab());
        mTabs.add(new MeTab());
        //创建MyFragmentPagerAdapter适配器（MyFragmentPagerAdapter是一个自定义的FragmentPagerAdapter）
        //getSupportFragmentManager是事务管理器
        mAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),mTabs);
        /*
        1.mViewPager用id绑定对应的viewpager
         2.为mViewPager设置一个Adapter，并传入MyFragmentPagerAdapter的引用mAdapter
         3.为mViewPager设置按钮监听和滑动监听
         */
        mViewPager=(ViewPager)findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);




        //MypagerAdapter adapter=new MypagerAdapter(viewList);
        //mViewPager.setAdapter(adapter);


        initView();
        //  initDatas();
        //  mViewPager.setAdapter(mAdapter);
        //initEvent();
    }

    /**
     * 初始化所有事件
     */
    private void initEvent()
    {

        //viewpager换页监听器
        mViewPager.setOnPageChangeListener(this);

    }





    private void initView()
    {
        // mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        ChangeColorIconWithText one = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mTabIndicators.add(one);
        ChangeColorIconWithText two = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mTabIndicators.add(two);
        ChangeColorIconWithText three = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mTabIndicators.add(three);
        ChangeColorIconWithText four = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void setOverflowButtonAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 设置menu显示icon
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
        {
            if (menu.getClass().getSimpleName().equals("MenuBuilder"))
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onClick(View v)
    {
        clickTab(v);


    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v)
    {
        resetOtherTabs();

        switch (v.getId())
        {
            case R.id.id_indicator_one:

                mTabIndicators.get(0).setIconAlpha(1.0f);
                //输出对应的viewlist中的view 参数0代表的是viewlist中的第一个view
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.id_indicator_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);

                break;
            case R.id.id_indicator_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs()
    {
        for (int i = 0; i < mTabIndicators.size(); i++)
        {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels)
    {
        // Log.e("TAG", "position = " + position + " ,positionOffset =  "
        // + positionOffset);
        if (positionOffset > 0)
        {
            ChangeColorIconWithText left = mTabIndicators.get(position);
            ChangeColorIconWithText right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int position)
    {
        // TODO Auto-generated method stub


    }

    @Override
    public void onPageScrollStateChanged(int state)
    {
        // TODO Auto-generated method stub


    }


    //系统返回键方法
    @Override
    public void onBackPressed() {
        if(lastClickTime<=0){
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
            lastClickTime=System.currentTimeMillis();
        }
        else {
            long currentClickTime=System.currentTimeMillis();
            if (currentClickTime-lastClickTime<1000){
                finish();

            }
            else {
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                lastClickTime=currentClickTime;
            }
        }
    }
}
