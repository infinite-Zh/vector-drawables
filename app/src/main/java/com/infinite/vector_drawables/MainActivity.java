package com.infinite.vector_drawables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView ivCompass, iv2, iv3;
    private StatusProgressView iv4;
    private AppCompatButton mProgress, mSuccess, mFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv4 = findViewById(R.id.statusView);
        ivCompass=findViewById(R.id.ivCompass);
        mProgress = findViewById(R.id.btnProgress);
        mSuccess = findViewById(R.id.btnSuccess);
        mFail = findViewById(R.id.btnFail);

        mProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv4.progress();
            }
        });
        mSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv4.success();
            }
        });
        mFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv4.fail();

            }
        });

        iv4.addCallback(new StatusProgressView.IAnimationCallback() {
            @Override
            public void onProgressStart() {

            }

            @Override
            public void onSuccessEnd() {
                Log.d("IAnimationCallback", "onSuccessEnd");
            }

            @Override
            public void onFailEnd() {
                Log.d("IAnimationCallback", "onFailEnd");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        ((AnimatedVectorDrawableCompat) ivCompass.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv2.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv3.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv4.getDrawable()).start();

        AnimatedVectorDrawableCompat a=AnimatedVectorDrawableCompat.create(this, R.drawable.anim_compass);
        ivCompass.setImageDrawable(a);
        a.start();
    }
}
