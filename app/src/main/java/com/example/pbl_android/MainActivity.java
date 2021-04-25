package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(fAuth.getCurrentUser() != null){
                    intent=new Intent(MainActivity.this, TrainerMainPage.class);
                }
                else{
                    intent=new Intent(MainActivity.this, SelectType.class);
                }

                startActivity(intent);
                finish();
            }
        },3000);
    }
}