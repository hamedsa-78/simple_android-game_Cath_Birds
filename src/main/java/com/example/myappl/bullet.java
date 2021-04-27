package com.example.myappl;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.myappl.R;

public class bullet{
    Bitmap bullet_img;
    public int x;
     private  final int y ,witdth,height;

    public int getY() {
        return y;
    }

    bullet(int x,int y, Resources res){
        bullet_img= BitmapFactory.decodeResource(res, R.drawable.bullet);
        witdth=bullet_img.getWidth()/4;
        height=bullet_img.getHeight()/4;
        bullet_img=Bitmap.createScaledBitmap(bullet_img,witdth,height,false);
        this.y=y;
        this.x=x;
    }

}
