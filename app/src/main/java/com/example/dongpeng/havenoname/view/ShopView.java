package com.example.dongpeng.havenoname.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.dongpeng.havenoname.R;
import com.example.dongpeng.havenoname.utils.ToastUtil;

/**
 * Created by dongpeng on 2017/1/17.
 */

public class ShopView extends View {
    private long downTime;
    private int mWidht;
    private int mHeight;
    private int text_size;
    private Paint mPaint,mPaint_Path;
    private int circle_width=8;
    private int bac_color;
    private boolean isShopButton=true;//是否是购物车按钮
    private ValueAnimator animator_bt,animator_del;
    private float fraction_bt=0,fraction_del;
    private Path path=new Path();
    private Region region_del,region_add;

    public ShopView(Context context) {
        super(context);
    }

    public ShopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShopView);
        bac_color = a.getColor(R.styleable.ShopView_bac_color, Color.YELLOW);
        text_size = a.getDimensionPixelSize(R.styleable.ShopView_text_size, 20);
        a.recycle();
        init();
    }

    private void init() {
        region_del=new Region();
        region_add=new Region();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(bac_color);
        mPaint_Path=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint_Path.setStyle(Paint.Style.STROKE);
        mPaint_Path.setStrokeWidth(circle_width);
        mPaint_Path.setColor(Color.GRAY);
        animator_bt=ValueAnimator.ofFloat(0,1);
        animator_bt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction_bt= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator_bt.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animator_del.start();
                isShopButton=false;
                invalidate();
            }
        });
        animator_bt.setInterpolator(new LinearInterpolator());
        animator_bt.setDuration(1000);
        animator_del=ValueAnimator.ofFloat(0,1);
        animator_del.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction_del= (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator_del.setDuration(1000);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                mWidht = Math.min(width, 200);
                break;
            case MeasureSpec.EXACTLY:
                mWidht = width;
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                mHeight = Math.min(height, 100);
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
            case MeasureSpec.UNSPECIFIED:

                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isShopButton){
            drawOne(canvas);//加入购物车按钮的背景
            drawBuyText(canvas);
        }else{
            drawTwo(canvas);
        }
    }

    /**
     * 添加减少商品数量
     * @param canvas
     */
    private void drawTwo(Canvas canvas) {
        path.reset();
        path.addCircle(fraction_del*(mHeight-mWidht)+mWidht-mHeight/2,mHeight/2,mHeight/2-circle_width/2, Path.Direction.CW);
        canvas.drawPath(path,mPaint_Path);
        region_del.setPath(path,new Region(0,0,mHeight,mHeight));
//        region_del.set(0,0,mHeight,mHeight);
        path.reset();
        path.addCircle(mWidht-mHeight/2,mHeight/2,mHeight/2, Path.Direction.CW);
        mPaint.setColor(bac_color);
        canvas.drawPath(path,mPaint);
        region_add.set(mWidht-mHeight,0,mWidht,mHeight);

    }

    /**
     * “加入购物车”
     * @param canvas
     */
    private void drawBuyText(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(text_size);
        float textWidth = mPaint.measureText("加入购物车");
        canvas.drawText("加入购物车", (mWidht - textWidth) / 2, (mHeight - (mPaint.descent() - mPaint.ascent())) / 2 - mPaint.ascent(), mPaint);
    }

    /**
     * 加入购物车按钮
     * @param canvas
     */
    private void drawOne(Canvas canvas) {
        mPaint.setColor(bac_color);
        RectF rectF = new RectF( fraction_bt*(mWidht-mHeight),0,mWidht , mHeight);
        canvas.drawRoundRect(rectF, mHeight / 2, mHeight / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime=System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                if (isShopButton){
                    if (System.currentTimeMillis()-downTime<500){
                        ToastUtil.show(getContext(),"加入购物车");
                        changeView();
                    }
                }else{
                    if (region_del.contains((int)event.getX(),(int)event.getY())){
                        ToastUtil.show(getContext(),"删除");
                    }else if (region_add.contains((int)event.getX(),(int)event.getY())){
                        ToastUtil.show(getContext(),"添加");
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void changeView() {
        animator_bt.start();
    }
}
