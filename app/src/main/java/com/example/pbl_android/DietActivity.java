package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DietActivity extends AppCompatActivity {

    Button bulkingbtn, leaningbtn;
    ImageView dietimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        bulkingbtn = findViewById(R.id.bulkingbtn);
        leaningbtn = findViewById(R.id.leaaningbtn);

        dietimg = findViewById(R.id.dietimage);

        bulkingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietimg.setImageResource(R.drawable.gaining);
            }
        });

        leaningbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietimg.setImageResource(R.drawable.leaning);
            }
        });

    }
}