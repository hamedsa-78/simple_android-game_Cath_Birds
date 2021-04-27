package com.example.myappl;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myappl.R;

public class Flight {
    int x,y,width,height,wingcounter=0;
    Bitmap flight1,flight2;
    Bitmap flight_shoot1;
    public int ind_shooted=-1;
    Bitmap flight_shoot2;
    Bitmap [] shooted_flight;
    Bitmap flight_shoot3;
    Bitmap flight_shoot4;
    Bitmap flight_shoot5;

    public boolean isgoingup;
    public boolean shooted=false;
    public boolean state=true;
    Flight(int screeny, Resources res){
        flight1= BitmapFactory.decodeResource(res,R.drawable.fly1);
        flight2=BitmapFactory.decodeResource(res,R.drawable.fly2);
        flight_shoot1=BitmapFactory.decodeResource(res,R.drawable.shoot1);
        flight_shoot2=BitmapFactory.decodeResource(res,R.drawable.shoot2);
        flight_shoot3=BitmapFactory.decodeResource(res,R.drawable.shoot3);
        flight_shoot4=BitmapFactory.decodeResource(res,R.drawable.shoot4);
        flight_shoot5=BitmapFactory.decodeResource(res,R.drawable.shoot5);


        width=flight1.getWidth();
        height=flight1.getHeight();
        width/=4;
        height/=4;
        y=screeny/2; //initial
        x=30;       // initial
        flight1=Bitmap.createScaledBitmap(flight1,width,height,false);
        flight2=Bitmap.createScaledBitmap(flight2,width,height,false);
        flight_shoot1=Bitmap.createScaledBitmap(flight_shoot1,width,height,false);
        flight_shoot2=Bitmap.createScaledBitmap(flight_shoot2,width,height,false);
        flight_shoot3=Bitmap.createScaledBitmap(flight_shoot3,width,height,false);
        flight_shoot4=Bitmap.createScaledBitmap(flight_shoot4,width,height,false);
        flight_shoot5=Bitmap.createScaledBitmap(flight_shoot5,width,height,false);
        shooted_flight=new Bitmap[5];
        shooted_flight[0]=flight_shoot1;
        shooted_flight[1]=flight_shoot2;
        shooted_flight[2]=flight_shoot3;
        shooted_flight[3]=flight_shoot4;
        shooted_flight[4]=flight_shoot5;
    }

  /*  public Bitmap get_shooted_flight(){
         ind_shooted++;
         if(ind_shooted>=5)
             ind_shooted=-1;
         if(ind_shooted>=0)
         return shooted_flight[ind_shooted];

        return null;
    }*/
  public Rect get_collesion_flight(){
      return new Rect(x,y,x+width,y+height);
  }
    public Bitmap get_flight(){
        if (wingcounter==0){
            wingcounter++;
            return flight1;
        }
        wingcounter--;
        return flight2;
    }
}
