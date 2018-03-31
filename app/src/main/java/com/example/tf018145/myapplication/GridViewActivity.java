package com.example.tf018145.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gridView extends AppCompatActivity {

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (GridView) findViewById(R.id.gridView);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getData(),R.layout.grid_item,new String[]{"img","title"},new int[]{R.id.gridViewImg,R.id.gridViewText});

        gridView.setAdapter(simpleAdapter);
    }

    public List<Map<String, Object>> getData() {
        List data = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.mipmap.ic_launcher);
            map.put("title","text"+i);
            data.add(map);
        }
        return  data;
    };
}
