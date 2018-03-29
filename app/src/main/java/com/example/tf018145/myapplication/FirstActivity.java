package com.example.tf018145.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TF018145 on 2018/3/23.
 */

public class FirstActivity extends AppCompatActivity {
    Button btn1;

    Button btn2;

    Button btn3;

    Button btn4;

    TextView textView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
        btn1 = (Button) findViewById(R.id.button5);
        btn2 = (Button) findViewById(R.id.button6);
        btn3 = (Button) findViewById(R.id.button7);
        btn4 = (Button) findViewById(R.id.button8);
        textView1 = (TextView) findViewById(R.id.firstActivityTextView1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, mainActivity.class);
                startActivity(intent);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, mainActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ListViewActivity.class);
                startActivity(intent);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DateTimePickerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 2) {
            String str = data.getStringExtra("data");
            textView1.setText(str);
        }
    }
}
