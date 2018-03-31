package com.example.tf018145.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBarH;
    Button add;
    Button reduce;
    Button reset;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        reset = (Button) findViewById(R.id.reset);
        progressBarH = (ProgressBar) findViewById(R.id.progressBar4);
        textView = (TextView) findViewById(R.id.textView);


        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);

        getProgress();
    }

    @Override
    public void onClick(View view) {
       int radom = (int)(Math.random()*10);
        int id = view.getId();
        switch (id) {
            case R.id.add:
                progressBarH.incrementProgressBy(radom);
                progressBarH.incrementSecondaryProgressBy(radom);
                break;
            case R.id.reduce:
                progressBarH.incrementProgressBy(-radom);
                progressBarH.incrementSecondaryProgressBy(-radom);
                break;
            case R.id.reset:
                progressBarH.setProgress(10);
                progressBarH.setSecondaryProgress(20);
                break;
        }
        getProgress();
    }

    public void getProgress() {
        int first = progressBarH.getProgress();
        int second = progressBarH.getSecondaryProgress();
        int max = progressBarH.getMax();
        Log.i("first", first+"");
        Log.i("second", second+"");
        Log.i("max", max+"");
        String firstPercent = 100*first / max  + "%";
        String secontPercent =100* second / max  + "%";
        textView.setText("第一进度条："+firstPercent+"; 第二进度条："+secontPercent);
    }
}
