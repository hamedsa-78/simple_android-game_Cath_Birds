package com.example.myappl;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Gameview extends SurfaceView implements Runnable {
    private Game_activity activity;
    public int score=0;
    private Thread thread;
     boolean isplaying=true;
    private Flight flight;
    private ArrayList<bullet> bullets;
  //  private birds[] Birds;
    private ArrayList<birds> Birds;
    private Paint paint;
    private birds bb;
    private int screenx, screeny;
    private Backgound backgound1, backgound2;
    private boolean is_GameOver=false;
    private SharedPreferences prefs;


    public Gameview(Game_activity activity, int screenx, int screeny) {
        super(activity);
        this.activity=activity;
        prefs=activity.getSharedPreferences("game",activity.MODE_PRIVATE);
        this.screenx = screenx;
        this.screeny = screeny;
        backgound1 = new Backgound(screenx, screeny, getResources());
        backgound2 = new Backgound(screenx, screeny, getResources());
        flight = new Flight(screeny, getResources());
        bullets = new ArrayList<>();
        Birds=new ArrayList<>();
      //  Birds = new birds[4];
        backgound2.x = screenx;
        //Bird_obj=new birds(com.example.myappl.Game_activity.class,screenx,screeny/2,getResources());
       /* Birds[0]=new birds(screenx,screeny/4,getResources());
        Birds[1]=new birds(screenx,screeny/2,getResources());
        Birds[2]=new birds(screenx,screeny/6,getResources());
        Birds[3]=new birds(screenx,screeny/8,getResources());*/
       /* for (int j = 0; j < 4; j++) {
            Birds[j].x = screenx;
        }
        Birds[0].y = screeny / 4;
        Birds[1].y = screeny / 2;
        Birds[2].y = screeny / 6;
        Birds[3].y = screeny / 8;*/
       Birds.add(new birds(screenx,screeny/2,getResources()));
       Birds.add(new birds(screenx,screeny/4,getResources()));
       Birds.add(new birds(screenx,screeny/6,getResources()));
       //Birds.add(new birds(screenx,screeny/8,getResources()));
       // bb = new birds(screenx, screeny / 2, getResources());

    }

    @Override

    public void run() {
        int high_=0;
        while (isplaying) {
            update(high_);
            draw();
            sleep();
        }
       /* if(is_GameOver){
            if(high_>this.High_score){
                this.High_score=high_;

            }
            Traverse_to_mother_activity();
        }
*/
    }

    private void Traverse_to_mother_activity() {
        if(prefs.getInt("highscore",0)<score){
            SharedPreferences.Editor editor=prefs.edit();
            editor.putInt("highscore",score);
            editor.apply();
        }
        Intent intent=new Intent(activity,MainActivity.class);
        //intent.putExtra("score", sc);
        activity.startActivity(intent);
    }

    public void resume() {
        isplaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        isplaying = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update(int high) {
        //***********************************************************
        backgound1.x -= 8;
        backgound2.x -= 8;
        if (backgound1.x + backgound1.background.getWidth() < 0) {
            backgound1.x = screenx;
        }                                                   //Background
        if (backgound2.x + backgound2.background.getWidth() < 0) {
            backgound2.x = screenx;
        }
        //************************************************************

        if (flight.isgoingup && !flight.state) {
            flight.y += 20;

            flight.state = true;                                  //Airplane
        }
        if (!flight.isgoingup && !flight.state) {
            flight.y -= 20;
            flight.state = true;
        }
        if (flight.y < 0) flight.y = 0;
        if (flight.y >= screeny - flight.height) flight.y = screeny - flight.height;
        //************************************************************

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).x += 10;                   //Bullets
        }

        //*************************************************************

        // for(int i=0;i<4;i++)
            /*if(Birds[i].x<=0){
                isplaying=false;
            }*/
           /* if(Birds[i].x>0)
                Birds[i].x-=Birds[i].get_speed();*/
            for (int i=0;i<Birds.size();i++){
                if (Birds.get(i).x <= 0-Birds.get(i).width){
                    is_GameOver=true;
                    isplaying = false;
                }

            }

        Random random=new Random();
        int speed=random.nextInt(10);
        if(speed<5) speed=5;
        for(int i=0;i<Birds.size();i++) {
            if (Birds.get(i).x > 0-Birds.get(i).width)
                Birds.get(i).x -= speed;

        }
        for(int j=0;j<Birds.size();j++) {
            if (esabat(bullets, Birds.get(j))) {
                Birds.get(j).x = screenx + 650;
                score++;
            }
        }

       /* if(esabat_air(Birds,flight))
        {
            is_GameOver=true;
        }*/

        for(int i=0;i<Birds.size();i++){
            if(Rect.intersects(flight.get_collesion_flight(),Birds.get(i).get_collesion_bird())){
                is_GameOver=true;
                break;

            }
        }

        /*
        if(Bird_obj.x<=0){
            Bird_obj.x-=10;
            isplaying=false;
        }
          if(Bird_obj.x>0){
             Bird_obj.x-=10;
          }
*/


    }  //*END OF UPDATE

    private void draw() {

        if (this.getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backgound1.background, backgound1.x, backgound1.y, paint);
            canvas.drawBitmap(backgound2.background, backgound2.x, backgound2.y, paint);
            if(!is_GameOver)
            canvas.drawBitmap(flight.get_flight(), flight.x, flight.y, paint);
            //canvas.drawBitmap(bb.Bird3,bb.x,bb.y,paint);
            if(is_GameOver){
                isplaying=false;
                canvas.drawBitmap(Birds.get(0).Bird2,flight.x,flight.y,paint);
                wait_before_exit();
                Traverse_to_mother_activity();

                getHolder().unlockCanvasAndPost(canvas);
                return;
            }
               // canvas.drawBitmap(Birds.get(0).Bird2,flight.x,flight.y,paint);
            for(int k=0;k<Birds.size();k++){

                canvas.drawBitmap(Birds.get(k).get_bird(),Birds.get(k).x,Birds.get(k).y,paint);
            }
            for (int j = 0; j < bullets.size(); j++) {
                canvas.drawBitmap(bullets.get(j).bullet_img, bullets.get(j).x + flight.width - 60, bullets.get(j).getY() + 80, paint);
            }
            flight.shooted = false;

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void wait_before_exit() {
        try {
            Thread.sleep(2000);
            activity.startActivity(new Intent(activity,MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(18);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //  switch (event.getAction())
        /*    case MotionEvent.ACTION_DOWN:
              //  if(event.getX()<screenx/2){
                    flight.isgoingup=false;
                    flight.state=false;
               // }
               break;
            case MotionEvent.ACTION_UP:
                flight.isgoingup=true;
                flight.state=false;
                break;
        }*/
        if (event.getY() > screeny / 2 && event.getX() < screenx / 2) {
            flight.isgoingup = true;
            flight.state = false;
        }
        if (event.getY() < screeny / 2 && event.getX() < screenx / 2) {
            flight.isgoingup = false;
            flight.state = false;
        }
        if (event.getX() > screenx / 2) {
            flight.shooted = true;
            bullets.add(new bullet(flight.x, flight.y, getResources()));
        }
        return true;

    }

    public boolean esabat(ArrayList<bullet> bullets, birds Bird_obj) {
        for (int i = 0; i < bullets.size(); i++) {
            if ((bullets.get(i).x > (Bird_obj.x - Bird_obj.width + 20) && bullets.get(i).x < Bird_obj.x) && (bullets.get(i).getY() < Bird_obj.y && bullets.get(i).getY() > Bird_obj.y - Bird_obj.height + 20)&& Bird_obj.x<=screenx) {

                return true;
                // }
            }


        }
        return false;
    }
    public boolean esabat_air(ArrayList<birds> Birds,Flight flight){
        for(int i=0;i<Birds.size();i++){
            if(Birds.get(i).x-Birds.get(i).width==flight.x+flight.width)
                return true;
        }
        return false;
    }
}