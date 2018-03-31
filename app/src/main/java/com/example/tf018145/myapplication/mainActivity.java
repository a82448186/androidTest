package com.example.tf018145.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.nio.charset.MalformedInputException;

public class mainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    ToggleButton toggleButton;
    String[] res = {"beijing", "shanghai", "guangzhou", "hangzhou"};

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Toast.makeText(mainActivity.this,"onTouchEvent1", Toast.LENGTH_LONG).show();
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Toast.makeText(mainActivity.this, "onTouchEvent", Toast.LENGTH_LONG).show();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        try {
            Toast.makeText(mainActivity.this, String.valueOf(event.getKeyCode()), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(mainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_test);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                btn1.requestFocus();
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                Toast.makeText(mainActivity.this, inputMethodManager.isActive()+"", Toast.LENGTH_SHORT).show();
            }
        });


        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                Toast.makeText(mainActivity.this, "打开键盘", Toast.LENGTH_SHORT).show();
            }
        });


        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(new MyOnClickListener() {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                Toast.makeText(mainActivity.this, "按钮3点击了", Toast.LENGTH_SHORT).show();
            }
        });

        btn4 = (Button) findViewById(R.id.button4);
        btn4.setOnClickListener(this);


        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, res);
        autoCompleteTextView.setAdapter(arrayAdapter);

        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);


        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setBackgroundResource(isChecked ? R.mipmap.ic_launcher : R.mipmap.ic_launcher_round);
            }
        });

        btn5 = (Button) findViewById(R.id.mainButton5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                EditText editText = (EditText) findViewById(R.id.editText);

                data.putExtra("data", editText.getText().toString());

                setResult(2, data);
                finish();
            }
        });

    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && this.getCurrentFocus() != null) {
            if (this.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mainActivity.this, "按钮4点击了", Toast.LENGTH_SHORT).show();
    }


//    /**
//     * 点击监听
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        onHideSoftInput(event);
//        return super.onTouchEvent(event);
//    }
//
//    /**
//     * 点击空白处,关闭输入法软键盘
//     */
//    public void onHideSoftInput(MotionEvent event)
//    {
//        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (event.getAction() == MotionEvent.ACTION_DOWN)
//        {
//            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
//            {
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//            }
//        }
//    }

}

class MyOnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        v.setAlpha(0.5f);
    }
}