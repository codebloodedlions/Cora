package com.example.cora.userInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.cora.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView coraBotLogo = findViewById(R.id.coraBotLogo);
        MotionLayout motionLayout = findViewById(R.id.motionLayout);

        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
        /*Animation scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.enlarge);
        coraBotLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coraBotLogo.startAnimation(scale);
            }
        });*/

    }
}