package com.infinite.vector_drawables;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

/**
 * @author bug小能手
 * Created on 2019/4/28.
 */
public class StatusProgressView extends AppCompatImageView {
    enum State {
        PROGRESS, SUCCESS, FAIL
    }

    private AnimatedVectorDrawableCompat mProgressDrawable, mSuccessCircleDrawable,mFailCircleDrawable, mSuccessCheckDrawable, mFailDrawable;
    private State mState;
    private IAnimationCallback mCallback;
    private int mProgressColor,mSuccessColor,mFailColor;

    public StatusProgressView(Context context) {
        this(context,null);
    }

    public StatusProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public StatusProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta=context.getTheme().obtainStyledAttributes(attrs, R.styleable.StatusProgressView, defStyleAttr, 0);
        mSuccessColor=ta.getColor(R.styleable.StatusProgressView_successColor, ContextCompat.getColor(context, R.color.colorPrimary));
        mProgressColor=ta.getColor(R.styleable.StatusProgressView_progressColor, ContextCompat.getColor(context, R.color.colorPrimary));
        mFailColor=ta.getColor(R.styleable.StatusProgressView_failColor, ContextCompat.getColor(context, R.color.colorPrimary));
        ta.recycle();
        init(context);

    }

    private void init(Context context) {

        mProgressDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_status_in_progress);
        mProgressDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.anim_status_in_progress);
        mSuccessCircleDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.ic_status_success_circle);
        mFailCircleDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.ic_status_success_circle);
        mSuccessCheckDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.ic_status_success_check);
        mFailDrawable = AnimatedVectorDrawableCompat.create(context, R.drawable.ic_status_fail);

        mSuccessCircleDrawable.setColorFilter(mSuccessColor, PorterDuff.Mode.SRC_IN);
        mSuccessCheckDrawable.setColorFilter(mSuccessColor, PorterDuff.Mode.SRC_IN);
        mProgressDrawable.setColorFilter(mProgressColor, PorterDuff.Mode.SRC_IN);
        mFailCircleDrawable.setColorFilter(mFailColor, PorterDuff.Mode.SRC_IN);
        mFailDrawable.setColorFilter(mFailColor, PorterDuff.Mode.SRC_IN);
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
        mFailCircleDrawable.stop();
        mFailDrawable.stop();
        mState = State.SUCCESS;
        refresh();
    }

    public void fail() {
        if (mState == State.FAIL) {
            return;
        }
        mSuccessCircleDrawable.stop();
        mSuccessCheckDrawable.stop();
        mState = State.FAIL;
        refresh();
    }

    public void addCallback(IAnimationCallback callback) {
        mCallback = callback;
    }

    private void refresh() {
        switch (mState) {
            case PROGRESS:
                setImageDrawable(mProgressDrawable);
                break;
            case SUCCESS:
                mProgressDrawable.stop();
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
                        mSuccessCheckDrawable.registerAnimationCallback(successCallback);
                        mSuccessCircleDrawable.unregisterAnimationCallback(this);
                    }
                });
                break;
            case FAIL:
                mProgressDrawable.stop();
                setImageDrawable(mFailCircleDrawable);
                mFailCircleDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationStart(Drawable drawable) {
                        super.onAnimationStart(drawable);
                    }

                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        setImageDrawable(mFailDrawable);
                        mFailDrawable.start();
                        mFailCircleDrawable.registerAnimationCallback(failCallback);
                        mFailCircleDrawable.unregisterAnimationCallback(this);
                    }
                });

                break;
        }
        ((AnimatedVectorDrawableCompat) getDrawable()).start();
    }

    private Animatable2Compat.AnimationCallback successCallback = new Animatable2Compat.AnimationCallback() {
        @Override
        public void onAnimationStart(Drawable drawable) {
            super.onAnimationStart(drawable);

        }

        @Override
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (mCallback != null) {
                mCallback.onSuccessEnd();
            }
            mSuccessCheckDrawable.unregisterAnimationCallback(this);
        }
    };
    private Animatable2Compat.AnimationCallback failCallback = new Animatable2Compat.AnimationCallback() {
        @Override
        public void onAnimationStart(Drawable drawable) {
            super.onAnimationStart(drawable);
        }

        @Override
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (mCallback != null) {
                mCallback.onFailEnd();
            }
            mFailDrawable.unregisterAnimationCallback(this);


        }
    };

    public interface IAnimationCallback {
        void onProgressStart();

        void onSuccessEnd();

        void onFailEnd();
    }
}
