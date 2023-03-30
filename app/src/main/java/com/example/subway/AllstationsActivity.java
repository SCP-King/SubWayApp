package com.example.subway;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subway.dao.SubWay;
import com.example.subway.service.SubWayService;
import com.example.subway.utils.StationnamesAdapter;

import java.util.ArrayList;
import java.util.List;
@SuppressLint("WrongViewCast")
public class AllstationsActivity extends AppCompatActivity implements View.OnClickListener {
    private String linename;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allstations);
        findViewById(R.id.find).setOnClickListener(this);
         Spinner spinner=findViewById(R.id.linename);
         List<String> list=new ArrayList<>();
         list.add("line1");
         list.add("line2");
         list.add("line3");
         list.add("line4");
         list.add("line5");
         list.add("line6");
         list.add("line7");
         list.add("line8");
         list.add("line9");
         list.add("line10");
         list.add("line11");
         list.add("line12");
         list.add("line13");
         list.add("line14");
         list.add("line15");
         list.add("line16");
         list.add("line17");
         list.add("line18");
         spinner.setSelection(0);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 linename=list.get(position);
                 Toast toast=Toast.makeText(getApplicationContext(), "您选择了"+linename, Toast.LENGTH_SHORT);
                 toast.show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {
             }
         });
    }

    @Override
    public void onClick(View v) {
        SubWay subWay=new SubWayService();
        List<String> stations=subWay.stations(linename);
        ListView listView=findViewById(R.id.stationnames);
        StationnamesAdapter stationnamesAdapter=new StationnamesAdapter(this,stations);
        listView.setAdapter(stationnamesAdapter);
    }
}
