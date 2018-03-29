package com.example.tf018145.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * Created by TF018145 on 2018/3/23.
 */

public class ListViewActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);
        listView = (ListView) findViewById(R.id.listView1);
        String[] from = {"listItemPic", "listItemText"};
        int[] to = {R.id.listItemPic, R.id.listItemText};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getData(), R.layout.list_item, from, to);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"1",Toast.LENGTH_LONG).show();
                Log.w("position", position+"");
                Map<String, String> a = (Map<String, String>)listView.getItemAtPosition(position);

                Log.w("a", a+"");
            }
        });
    }

    public List<Map<String, String>> getData() {
        List list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("listItemPic", R.mipmap.ic_launcher);
            map.put("listItemText", "hellow" + i);
            list.add(map);
        }
        return list;
    }
}
