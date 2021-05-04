package com.example.pbl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DietActivity extends AppCompatActivity {

    Button bulkingbtn, leaningbtn;
    ImageView dietimg;
    TextView instructiontxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        bulkingbtn = findViewById(R.id.bulkingbtn);
        leaningbtn = findViewById(R.id.leaaningbtn);
        instructiontxt = findViewById(R.id.instructiontext);

        dietimg = findViewById(R.id.dietimage);

        bulkingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietimg.setImageResource(R.drawable.gaining);
                instructiontxt.setVisibility(View.VISIBLE);
            }
        });

        leaningbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietimg.setImageResource(R.drawable.leaning);
                instructiontxt.setVisibility(View.VISIBLE);
            }
        });

    }
}