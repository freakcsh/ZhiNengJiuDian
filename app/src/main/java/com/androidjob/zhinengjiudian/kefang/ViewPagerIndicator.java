package com.androidjob.zhinengjiudian.kefang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.androidjob.zhinengjiudian.R;

/**
 * Created by thinkpad on 2016/8/15.
 */
public class ViewPagerIndicator extends LinearLayout{
    private Paint mPaint;
    private Path mPath;
    private int mTriangleWidth;
    private int mTriangleHeight;
    private static final float RADIO_TRIANGLE_WIDTH=1/6F;
    private int mInitTranslationX;
    private int mTranslationX;
    private int mTabVisibleCount;
    private static final int COUNT_DEFAULT_TAB=3;
    public ViewPagerIndicator(Context context){
        this(context,null);
    }
    public ViewPagerIndicator(Context context, AttributeSet attrs){
        super(context,attrs);
        //获取可见tab的数量
        TypedArray a= context.obtainStyledAttributes(attrs, R.styleable.ViewPageIndicator);
        mTabVisibleCount=a.getInt(R.styleable.ViewPageIndicator_visible_tab_count,COUNT_DEFAULT_TAB);
        if (mTabVisibleCount<0){
            mTabVisibleCount=COUNT_DEFAULT_TAB;
        }
        a.recycle();
        //初始化画笔
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }
    //绘制三角形
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mInitTranslationX+mTranslationX,getHeight()+2);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        mTriangleWidth=(int)(w/mTabVisibleCount*RADIO_TRIANGLE_WIDTH);
        mInitTranslationX=w/mTabVisibleCount/2-mTriangleWidth/2;
        mTriangleHeight=mTriangleWidth/2;
        ininTrangle();

    }
    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        int cCOUNT=getChildCount();
        if (cCOUNT==0)return;
        for (int i=0;i<cCOUNT;i++){
            View view=getChildAt(i);
            LinearLayout.LayoutParams lp= (LayoutParams) view.getLayoutParams();
            lp.weight=0;
            lp.width=getScreenWidth()/mTabVisibleCount;
            view.setLayoutParams(lp);
        }
    }
    //获得屏幕的宽度
    private int getScreenWidth(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    //初始化三角形
    private void ininTrangle(){
        mPath=new Path();
        mPath.moveTo(0,0);
        mPath.lineTo(mTriangleWidth,0);
        mPath.lineTo(mTriangleWidth/2,-mTriangleHeight);
        mPath.close();
    }

    //指示器更随手指进行移动
    public void scroll(int position,float offset){
        int tabWidth=getWidth()/mTabVisibleCount;
        mTranslationX=(int)(tabWidth*(offset+position));
        //容器移动，在tab处于移动到最后一个时
        if (position>=(mTabVisibleCount-2)&&offset>0&&getChildCount()>mTabVisibleCount){
            if (mTabVisibleCount!=1){
                this.scrollTo(
                        (position-(mTabVisibleCount-2))*tabWidth+(int)(tabWidth*offset),0);
            }else {
                this.scrollTo(position*tabWidth+(int)(tabWidth*offset),0);
            }

        }
        invalidate();
    }
}
