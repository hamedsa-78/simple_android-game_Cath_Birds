package com.example.myappl;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myappl.R;

public class Backgound {
    int x=0,y=0;
    Bitmap background;

    Backgound(int screenx, int screeny, Resources res){
        background= BitmapFactory.decodeResource(res, R.drawable.gameback);
        background= Bitmap.createScaledBitmap(background,screenx,screeny,false);
    }
}
