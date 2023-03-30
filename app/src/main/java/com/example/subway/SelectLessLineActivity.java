package com.example.subway;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subway.dao.SubWay;
import com.example.subway.pojo.Station;
import com.example.subway.service.SubWayService;
import com.example.subway.utils.StationAdapter;

import java.util.Collections;
import java.util.List;

public class SelectLessLineActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessline);
        findViewById(R.id.find).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editText=null;
        editText=findViewById(R.id.startstation);
        String start=editText.getText().toString();
        editText=findViewById(R.id.endstation);
        String end=editText.getText().toString();
        SubWay subWay=new SubWayService();
        List<Station> stations=subWay.getLessLines(start,end);
        Station a=new Station();
        Station b=new Station();
        Station c=new Station();
        for(int i=1;i<stations.size()-1;i++){
            a=stations.get(i-1);
            b=stations.get(i);
            c=stations.get(i+1);
            if(Collections.disjoint(a.getLines(),c.getLines())){
                for(String j:c.getLines()){
                    if(b.getLines().contains(j)){
                        stations.get(i).setStationname(b.getStationname()+"(换乘"+j+")");
                        break;
                    }
                }
            }
        }
        ListView listView=findViewById(R.id.stations);
        StationAdapter stationAdapter=new StationAdapter(this,stations);
        listView.setAdapter(stationAdapter);
    }
}
