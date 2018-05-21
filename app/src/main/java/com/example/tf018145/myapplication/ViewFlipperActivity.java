package com.example.tf018145.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    int[] imgs = {R.mipmap.flipper1,R.mipmap.flipper2,R.mipmap.flipper3,R.mipmap.flipper4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        viewFlipper = findViewById(R.id.viewFlipper);

        for(int i = 0;i<imgs.length;i++) {
            viewFlipper.addView(getImageView(imgs[i]));
        }
        viewFlipper.setFlipInterval(3000);

        viewFlipper.startFlipping();

    }

    protected ImageView getImageView (int resource) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resource);
        return  imageView;
    }
}
