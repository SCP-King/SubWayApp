package com.example.subway.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subway.R;

import java.util.List;

public class StationnamesAdapter extends BaseAdapter {
    private List<String> stationnames;
    private Context context;
    public StationnamesAdapter(Context context,List<String> stationnames){
        this.stationnames=stationnames;
        this.context=context;
    }
    @Override
    public int getCount() {
        return stationnames.size();
    }

    @Override
    public Object getItem(int position) {
        return stationnames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.stationnames,null);
        String stationname=stationnames.get(position);
        TextView textView=null;
        textView= view.findViewById(R.id.stationname);
        textView.setText(stationname);
        return view;
    }
}
