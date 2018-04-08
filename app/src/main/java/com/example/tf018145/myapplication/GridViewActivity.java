package com.example.tf018145.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewActivity extends AppCompatActivity {

    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView = (GridView) findViewById(R.id.gridView);

        SimpleAdapter simpleAdapter = new GrivAdapter(this,getData(),R.layout.grid_item,new String[]{"img","title"},new int[]{R.id.gridViewImg,R.id.gridViewText});

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
}
