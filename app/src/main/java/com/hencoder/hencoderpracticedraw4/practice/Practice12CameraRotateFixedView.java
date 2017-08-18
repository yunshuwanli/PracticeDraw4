package com.hencoder.hencoderpracticedraw4.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice12CameraRotateFixedView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point1 = new Point(200, 200);
    Point point2 = new Point(600, 200);

    public Practice12CameraRotateFixedView(Context context) {
        super(context);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
    }

    Camera camera = new Camera();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //正常的逻辑是 旋转之前绘制内容平移到轴心 然后旋转之后把投影移动回来
        // 但是canvas的变化顺序是相反的 所以···
        canvas.save();
        camera.save();
        camera.rotateX(30);
        canvas.translate((point1.x + bitmap.getWidth() / 2), (point1.y + bitmap.getHeight() / 2));//旋转之后把投影移动回来
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-(point1.x + bitmap.getWidth() / 2),-( point1.y + bitmap.getHeight() / 2));// 旋转之前绘制内容平移到轴心
        canvas.drawBitmap(bitmap, point1.x, point1.y, paint);
        canvas.restore();

        canvas.save();
        camera.save();
        camera.rotateY(30);
        canvas.translate(point2.x + bitmap.getWidth() / 2, point2.y + bitmap.getHeight() / 2);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-(point2.x + bitmap.getWidth() / 2), -(point2.y + bitmap.getHeight() / 2));
        canvas.drawBitmap(bitmap, point2.x, point2.y, paint);
        canvas.restore();
    }
}
