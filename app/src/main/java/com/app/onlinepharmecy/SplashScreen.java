package com.app.onlinepharmecy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.app.onlinepharmecy.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        Thread th=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int p=0;p<=100;p=p+3){
                    try {
                        Thread.sleep(40);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}




                    Intent i2=new Intent(SplashScreen.this,SignIn.class);
                    startActivity(i2);
                    finish();






            } });
        th.start();



    }
}