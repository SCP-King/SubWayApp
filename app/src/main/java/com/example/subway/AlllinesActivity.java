package com.example.subway;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subway.dao.SubWay;
import com.example.subway.service.SubWayService;
import com.example.subway.utils.LinenameAdapter;

import java.util.List;

public class AlllinesActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllines);
        findViewById(R.id.find).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText=findViewById(R.id.stationname);
        String stationname=editText.getText().toString();
        SubWay subWay=new SubWayService();
        List<String> lines=subWay.lines(stationname);
        ListView listView=findViewById(R.id.lines);
        LinenameAdapter lineAdapter=new LinenameAdapter(this,lines);
        listView.setAdapter(lineAdapter);
    }
}
