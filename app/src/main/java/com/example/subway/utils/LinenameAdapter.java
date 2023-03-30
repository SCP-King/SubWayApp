package com.example.subway.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.subway.R;

import java.util.List;

public class LinenameAdapter extends BaseAdapter {
    private Context context;
    private List<String> lines;

    public LinenameAdapter(Context context, List<String> lines) {
        this.context = context;
        this.lines = lines;
    }

    @Override
    public int getCount() {
        return lines.size();
    }

    @Override
    public Object getItem(int position) {
        return lines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.lines,null);
        TextView textView=view.findViewById(R.id.line);
        textView.setText(lines.get(position));
        return view;
    }
}
