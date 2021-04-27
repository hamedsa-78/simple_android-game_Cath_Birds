package com.example.myappl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        public int score=0;
        TextView high;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        high=findViewById(R.id.high);
       // high.setText(score);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Game_activity.class);
                startActivity(intent);
            }
        });
        high=findViewById(R.id.high);
        SharedPreferences prefs=getSharedPreferences("game",MODE_PRIVATE);
        high.setText("High score :"+prefs.getInt("highscore",0));
    }

    @Override
    protected void onResume() {
       // Toast.makeText(this,"returned",Toast.LENGTH_SHORT).show();
        super.onResume();
        int  highscore=score;
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            if(extras.containsKey("score")) {
                highscore=extras.getInt("score");
            }
        }
      //  high.setText(highscore);
    }
}
