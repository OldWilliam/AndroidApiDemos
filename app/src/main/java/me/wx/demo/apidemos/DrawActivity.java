package me.wx.demo.apidemos;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;


public class DrawActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        //画仪表盘
        final FrameLayout dashLayout = (FrameLayout) findViewById(R.id.dashboard_layout);
        View dashboardView = new View(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                //外圆
                Paint paintCircle = new Paint();
                paintCircle.setStyle(Paint.Style.STROKE);
                paintCircle.setAntiAlias(true);
                paintCircle.setStrokeWidth(5);
                int mWidth = getMeasuredHeight();
                int mHeight = mWidth;
                canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCircle);
                //刻度
                Paint paintDegree = new Paint();
                paintDegree.setStrokeWidth(3);
                for (int i = 0; i < 24; i++) {
                    //区分整点与非整点
                    if (i == 0 || i == 6 || i == 12 || i == 18) {
                        paintDegree.setStrokeWidth(5);
                        paintDegree.setTextSize(30);
                        canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                        String degree = String.valueOf(i);
                        canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 90, paintDegree);
                    } else {
                        paintDegree.setStrokeWidth(3);
                        paintDegree.setTextSize(15);
                        canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2, mWidth / 2, mHeight / 2 - mWidth / 2 + 30, paintDegree);
                        String degree = String.valueOf(i);
                        canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2, mHeight / 2 - mWidth / 2 + 60, paintDegree);
                    }
                    //通过旋转简化坐标运算，用来画有角度的线
                    canvas.rotate(15,mWidth/2,mHeight/2);
                }

                //画指针
                Paint paintHour = new Paint();
                paintHour.setStrokeWidth(20);
                Paint paintMinute = new Paint();
                paintMinute.setStrokeWidth(10);
                canvas.save();
                canvas.translate(mWidth / 2, mHeight / 2);
                canvas.drawLine(0, 0, 100, 100, paintHour);
                canvas.drawLine(0, 0, 100, 200, paintMinute);
                canvas.restore();
            }
        };
        dashLayout.addView(dashboardView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        //Layer图层
        FrameLayout layerLayout = (FrameLayout) findViewById(R.id.layer_layout);
        View layerView = new View(this){
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                Paint mPaint = new Paint();
                canvas.drawColor(Color.WHITE);
                mPaint.setColor(Color.BLUE);
                canvas.drawCircle(150, 150, 100, mPaint);

                canvas.saveLayerAlpha(0, 0, 400, 400, 255, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
                mPaint.setColor(Color.RED);
                canvas.drawCircle(200, 200, 100, mPaint);
                canvas.restore();
            }
        };
        layerLayout.addView(layerView);
    }
}
