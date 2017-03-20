package com.example.felixxiao.drawsquaretest;

/**
 * Created by FelixXiao on 2017/3/20.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View implements Runnable {

    public final static String TAG = "Example_05_03_GameView";
    // 声明Paint对象
    private Paint mPaint = null;

    public GameView(Context context) {
        super(context);
        // 构建对象
        mPaint = new Paint();

        // 开启线程
        new Thread(this).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 设置Paint为无锯齿
        mPaint.setAntiAlias(true);



        // 设置paint的颜色和Alpha值(a,r,g,b)
        mPaint.setAlpha(220);

        // 这里可以设置为另外一个paint对象
        // mPaint.set(new Paint());
        // 设置字体的尺寸
        mPaint.setTextSize(14);


        // 得到Paint的一些属性 颜色、Alpha值、外框的宽度、字体尺寸
//        Log.i("TAG", "paint Color------>" + mPaint.getColor());
//        Log.i(TAG, "paint Alpha------->" + mPaint.getAlpha());
//        Log.i("TAG", "paint StrokeWidth--------->" + mPaint.getStrokeWidth());
//        Log.i("TAG", "paint TextSize----------->" + mPaint.getTextSize());

        int[][] matrix = new int[10][10];
        Random rand = new Random();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                matrix[i][j] = rand.nextInt(2);
            }
        }

        // 设置风格为实心
        mPaint.setStyle(Paint.Style.FILL);

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(matrix[i][j] == 0) {
                    mPaint.setColor(Color.GREEN);
                }
                else {
                    mPaint.setColor(Color.RED);
                }
                canvas.drawRect(300 + j*40, 400 + i*40, 300 + j*40 + 40, 400 + i*40 + 40, mPaint);
            }
        }



//        mPaint.setColor(Color.GREEN);
//
//        // 绘制绿色实心矩形
//        canvas.drawRect(300, 400, 300 + 40, 400 + 40, mPaint);
//        canvas.drawRect(340, 400, 340 + 40, 400 + 40, mPaint);
//
//        mPaint.setColor(Color.RED);
//        canvas.drawRect(380, 400, 380 + 40, 400 + 40, mPaint);
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            // 更新界面
            postInvalidate();
        }
    }
}