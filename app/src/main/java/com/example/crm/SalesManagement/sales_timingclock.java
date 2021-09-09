package com.example.crm.SalesManagement;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.example.crm.R;

public class sales_timingclock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_timingclock);
        LottieAnimationView animationView
                = findViewById(R.id.animation_view);
        animationView.addAnimatorUpdateListener(
                        (animation) -> {// Do something.
                        });
        animationView.playAnimation();
        if (animationView.isAnimating()) {
            // Do something.
        }
        ValueAnimator animator
                = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> {
                    animationView.setProgress((Float) animation.getAnimatedValue());
                });
        animator.start();
    }
}