package com.infinite.vector_drawables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView iv1, iv2, iv3;
    private StatusProgressView iv4;
    private AppCompatButton mProgress, mSuccess, mFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.statusView);
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
//        ((AnimatedVectorDrawableCompat) iv1.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv2.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv3.getDrawable()).start();
//        ((AnimatedVectorDrawableCompat) iv4.getDrawable()).start();
    }
}
