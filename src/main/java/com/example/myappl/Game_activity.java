package com.example.myappl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Game_activity extends AppCompatActivity {
        private Gameview gameview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameview=new Gameview(this,point.x,point.y);
        setContentView(gameview);

    }

    @Override
    protected void onResume() {
        super.onResume();
       /*      if(!gameview.isplaying)
        {
            Intent intent=new Intent(Game_activity.this,MainActivity.class);
            startActivity(intent);
        }*/
        gameview.resume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        gameview.pause();

    }
}
