package com.example.subway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.b1).setOnClickListener(this);
        findViewById(R.id.b2).setOnClickListener(this);
        findViewById(R.id.b3).setOnClickListener(this);
        findViewById(R.id.b4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b1:
                startActivity(new Intent(MainActivity.this,AllstationsActivity.class));
                break;
            case R.id.b2:
                startActivity(new Intent(MainActivity.this,AlllinesActivity.class));
                break;
            case R.id.b3:
                startActivity(new Intent(MainActivity.this,SelectShortLineActivity.class));
                break;
            case R.id.b4:
                startActivity(new Intent(MainActivity.this,SelectLessLineActivity.class));
                break;
        }
    }
}