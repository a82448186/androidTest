package com.example.tf018145.myapplication;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StaticFragment extends Fragment {
    TextView textView;
    Button btn;
    FragmentActivity fragmentActivity;

    public interface MyListener {
        public void dispatch(String str);
    }

    @Override
    public void onAttach(Context context) {
        this.fragmentActivity = (FragmentActivity)context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_static, container, false);
        textView = view.findViewById(R.id.textView2);

        btn = view.findViewById(R.id.button10);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentActivity.dispatch("fragment调用了activity的方法");
            }
        });

        String text = getArguments().get("text") + "";
        Toast.makeText(fragmentActivity, "获取到传入参数：" + text, Toast.LENGTH_LONG).show();
        textView.setText(text);
        return view;
    }
}