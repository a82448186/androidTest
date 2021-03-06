package com.example.tf018145.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity {

    GridView gridView;
    SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (GridView) findViewById(R.id.gridView);

        simpleAdapter = new GrivAdapter(this,getData(),R.layout.grid_item,new String[]{"img","title"},new int[]{R.id.gridViewImg,R.id.gridViewText});

        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> map = (Map<String, Object>)simpleAdapter.getItem(i);
                Intent intent = new Intent(GridViewActivity.this, (Class)map.get("class"));
                startActivity(intent);
            }
        });
        getJson();
    }

    public List<Map<String, Object>> getData() {
        List data = new ArrayList<>();
        Class[] classes = {
                mainActivity.class,
                ListViewActivity.class,
                DateTimePickerActivity.class,
                GridViewActivity.class,
                ProgressBarActivity.class,
                RfidActivity.class,
                FragmentActivity.class,
                ViewPagerActivity.class,
                ViewFlipperActivity.class,
                ScrollerViewActivity.class
        };
        String[] titles = {
                "main", "ListView","DateTimePicker","GridView","ProgressBar",
                "Rfid","Fragment","viewPager","viewFillper","scrollerView"
        };
        for (int i = 0; i <classes.length ; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", R.mipmap.ic_launcher);
            map.put("title",titles[i]);
            map.put("class", classes[i]);
            data.add(map);
        }
        return  data;
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Toast.makeText(this, event.getKeyCode(), Toast.LENGTH_LONG).show();
        return super.dispatchKeyEvent(event);
    }

    public class GrivAdapter extends SimpleAdapter {

        /**
         * Constructor
         *
         * @param context  The context where the View associated with this SimpleAdapter is running
         * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
         *                 Maps contain the data for each row, and should include all the entries specified in
         *                 "from"
         * @param resource Resource identifier of a view layout that defines the views for this list
         *                 item. The layout file should include at least those named views defined in "to"
         * @param from     A list of column names that will be added to the Map associated with each
         *                 item.
         * @param to       The views that should display column in the "from" parameter. These should all be
         *                 TextViews. The first N views in this list are given the values of the first N columns
         */
        public GrivAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

             View view =  super.getView(position, convertView, parent);
            WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(displayMetrics.widthPixels/3,displayMetrics.widthPixels/3);
            view.setLayoutParams(params);
            return  view;
        }
    }

    public void getJson () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://47.98.166.6/version.json").build();
                try {
                    Response response = client.newCall(request).execute();
                    String s = response.body().string();
                    String a = (String) JSONObject.parseObject(s).get("versionCode");
                    Log.i("version", a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
