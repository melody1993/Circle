package com.example.wang55.circleprogressview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.wang55.circleprogressview.R;

/**
 * Created by wang55 on 2016/11/21.
 */
public class CircleProgressView extends View{
    private int mMaxProgress=100;//总进度
    private int mCurProgress=0;//当前进度
    private int mStrokeWidth;//圆环宽度
    private int textColor;
    private int progressColor;
    private int roundColor;
    private float textSize;
    private String text;
    //画圆所在的矩形区域
    private  RectF mRectF;
    private  Paint mPaint;
    private Context mContext;
    private  int width;
    private  int height;
    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //构建画笔和矩形对象
        mContext=context;
        mRectF=new RectF();
        mPaint=new Paint();
        //获取自定义属性
        TypedArray mType=context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        roundColor=mType.getColor(R.styleable.CircleProgressView_roundColor, Color.GRAY);
        progressColor=mType.getColor(R.styleable.CircleProgressView_roundProgressColor,Color.RED);
        textColor=mType.getColor(R.styleable.CircleProgressView_textColor,Color.BLACK);
        textSize=mType.getDimension(R.styleable.CircleProgressView_textSize,18);
        mStrokeWidth=mType.getInteger(R.styleable.CircleProgressView_roundWidth,8);
        text=mType.getString(R.styleable.CircleProgressView_textContent);
        mType.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         width = this.getWidth();
         height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }
        //设置画笔相关属性
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setColor(roundColor);
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        //图形位置
        mRectF.left=mStrokeWidth/2;
        mRectF.top=mStrokeWidth/2;
        mRectF.right=width-mStrokeWidth/2;
        mRectF.bottom=height-mStrokeWidth/2;
        canvas.drawArc(mRectF,-90,360,false,mPaint);//true 表示画扇形
        //画进度条
        mPaint.setColor(progressColor);
        canvas.drawArc(mRectF,-90,((float) mCurProgress/mMaxProgress)*360,false,mPaint);
        //设置文字
        mPaint.setStrokeWidth(0);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        float textwidth=mPaint.measureText(text);
        canvas.drawText(text,width/2-textwidth/2,height/2+textSize/2,mPaint);
    }
    public void setCurProgress(int progress){
        this.mCurProgress=progress;
        this.postInvalidate();//在工作线程里更新图像
    }
    public void setTextContent(String txt){
        this.text=txt;
        this.postInvalidate();
    }
}
