package pro.mbed.android.areyparrot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by m_chichi on 26.02.2018.
 */

public class DrawOscView extends View{
    Paint paint;
//    List<Integer> data = new ArrayList<>(200);
//    Bitmap bitmap;
//    Canvas mCanvas;
    int rms, peak;
    int[] data = new int[200];
    int index = 0;

    public DrawOscView(Context context) {
        super(context);
        init();
    }

    public DrawOscView(Context context, @Nullable AttributeSet attrs, Paint paint) {
        super(context, attrs);
        init();
    }

    public DrawOscView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setDither(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
    }

    public void updateCanvas(final int peak,final  int rms){
        this.peak = (9600 + peak)/10;
        this.rms = (9600 + rms)/10;
        data[index++] = this.peak;
        if (index >= 200) index = 0;
        data[index] = 0;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if(bitmap == null) {
//            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
//        }
//        if(canvas == null) {
//            canvas = new Canvas(bitmap);
//        }
//        canvas.drawText("Width" + canvas.getWidth(),200,300, paint);
//        canvas.drawText("Height" + canvas.getHeight(),500,300, paint);

//        canvas.drawLine(10, 20, rms, 300, paint);
        int x1 = 0;
        for (Integer i: data) {
            canvas.drawLine(x1, 0, x1, i, paint);
            x1 += 5;
        }
    }

}
