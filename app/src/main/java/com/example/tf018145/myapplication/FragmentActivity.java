package com.example.tf018145.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentActivity extends AppCompatActivity implements StaticFragment.MyListener {
    Button fragmentBtn;

    EditText fragmentEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        fragmentBtn = (Button) findViewById(R.id.fragmentBtn);

        fragmentEdit = (EditText) findViewById(R.id.fragmentEdit);

        fragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = fragmentEdit.getText() + "";
                StaticFragment fragmentActivity = new StaticFragment();
                Bundle bundle = new Bundle();
                bundle.putString("text", text);
                fragmentActivity.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentActivity);
                transaction.commit();
            }
        });
    }

    @Override
    public void dispatch(String str) {
        Toast.makeText(this,str, Toast.LENGTH_LONG).show();
    }
}
