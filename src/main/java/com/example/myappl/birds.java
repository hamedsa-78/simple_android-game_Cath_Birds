package com.example.myappl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myappl.R;

import java.util.Random;

public class birds {

    public Bitmap Bird1;
    public Bitmap Bird2;
    public Bitmap Bird3;
    private int  bird_number=0;
    public Bitmap Bird4;
    public int height,width,x,y;
    public int speed=10;
    private Random random;

    public birds(int x, int y, Resources res) {
        this.x=x;
        this.y=y;

        Bird1= BitmapFactory.decodeResource(res, R.drawable.bird1);
        width=Bird1.getWidth()/8;
        height=Bird1.getHeight()/8;
        Bird1=Bitmap.createScaledBitmap(Bird1,width,height,false);

        Bird2= BitmapFactory.decodeResource(res, R.drawable.bird2);
        Bird2=Bitmap.createScaledBitmap(Bird2,width,height,false);

        Bird3= BitmapFactory.decodeResource(res, R.drawable.bird3);
        Bird3=Bitmap.createScaledBitmap(Bird3,width,height,false);

        Bird4= BitmapFactory.decodeResource(res, R.drawable.bird4);
        Bird4=Bitmap.createScaledBitmap(Bird4,width,height,false);
    }
    public Bitmap get_bird(){
        if(bird_number==0){
            bird_number++;
            return Bird1;

        }
       if(bird_number==1){
           bird_number++;
           return Bird2;
       }
       if(bird_number==2){
           bird_number++;
           return Bird3;
       }
        if(bird_number==3) {
            bird_number=0;
            return Bird4;
        }
       return null;
    }
    public Rect get_collesion_bird(){
        return new Rect(x,y,x+width,y+height);
    }
    public int  get_speed(){
        int sp = random.nextInt(30);
        if(sp<6)
            sp=6;
        return sp;
    }
}
