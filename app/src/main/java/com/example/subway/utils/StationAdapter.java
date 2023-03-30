package com.example.subway.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subway.R;
import com.example.subway.pojo.Station;

import java.util.List;

public class StationAdapter extends BaseAdapter {
    private Context context;
    private List<Station> stations;

    public StationAdapter(Context context, List<Station> stations) {
        this.context = context;
        this.stations = stations;
    }

    @Override
    public int getCount() {
        return stations.size();
    }

    @Override
    public Object getItem(int position) {
        return stations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.onestation,null);
       TextView textView=view.findViewById(R.id.station);
        textView.setText(stations.get(position).getStationname());
        return view;
    }
}
