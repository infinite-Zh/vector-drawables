package com.infinite.vector_drawables;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

/**
 * @author kfzhangxu
 * Created on 2019/4/28.
 */
public class StatusProgressView extends AppCompatImageView {
    enum State {
        PROGRESS, SUCCESS, FAIL
    }

    private AnimatedVectorDrawableCompat mProgressDrawable, mSuccessCircleDrawable, mSuccessCheckDrawable,mFailDrawable;
    private State mState ;

    public StatusProgressView(Context context) {
        super(context);
        init(context);
    }

    public StatusProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public StatusProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {

        mProgressDrawable=AnimatedVectorDrawableCompat.create(context,R.drawable.anim_status_in_progress);
        mProgressDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_status_in_progress);
        mSuccessCircleDrawable = AnimatedVectorDrawableCompat.create( context,R.drawable.ic_status_success_circle);
        mSuccessCheckDrawable = AnimatedVectorDrawableCompat.create( context,R.drawable.ic_status_success_check);
        mFailDrawable = AnimatedVectorDrawableCompat.create( context,R.drawable.ic_status_fail);
        progress();
        refresh();
    }

    public void progress() {
        if (mState == State.PROGRESS) {
            return;
        }
        mState = State.PROGRESS;
        refresh();
    }

    public void success() {
        if (mState == State.SUCCESS) {
            return;
        }
        mState = State.SUCCESS;
        refresh();
    }

    public void fail() {
        if (mState == State.FAIL) {
            return;
        }
        mState = State.FAIL;
        refresh();
    }

    private void refresh() {
        switch (mState) {
            case PROGRESS:
                setImageDrawable(mProgressDrawable);
                break;
            case SUCCESS:
                setImageDrawable(mSuccessCircleDrawable);
                mSuccessCircleDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationStart(Drawable drawable) {
                        super.onAnimationStart(drawable);
                    }

                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        setImageDrawable(mSuccessCheckDrawable);
                        mSuccessCheckDrawable.start();
                    }
                });
                break;
            case FAIL:
                setImageDrawable(mSuccessCircleDrawable);
                mSuccessCircleDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationStart(Drawable drawable) {
                        super.onAnimationStart(drawable);
                    }

                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        setImageDrawable(mFailDrawable);
                        mFailDrawable.start();
                    }
                });

                break;
        }
        ((AnimatedVectorDrawableCompat) getDrawable()).start();
    }

}
