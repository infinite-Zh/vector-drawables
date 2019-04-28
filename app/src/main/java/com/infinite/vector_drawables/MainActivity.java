package com.infinite.vector_drawables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageView iv1,iv2,iv3,iv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1=findViewById(R.id.iv1);
        iv2=findViewById(R.id.iv2);
        iv3=findViewById(R.id.iv3);
        iv4=findViewById(R.id.iv4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((AnimatedVectorDrawableCompat)iv1.getDrawable()).start();
        ((AnimatedVectorDrawableCompat)iv2.getDrawable()).start();
        ((AnimatedVectorDrawableCompat)iv3.getDrawable()).start();
        ((AnimatedVectorDrawableCompat)iv4.getDrawable()).start();
    }
}
