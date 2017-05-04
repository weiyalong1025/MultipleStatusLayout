package com.winsion.multiplestatuslayout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wyl on 2017/5/4.
 */

public class MultipleStatusLayout extends FrameLayout {
    private ChildView loadingView;
    private ChildView errorView;
    private ChildView emptyView;
    private View contentView;

    public MultipleStatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public MultipleStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addMultipleStatusLayout(context);
    }

    private void addMultipleStatusLayout(Context context) {
        initLoadingView(context);
        initErrorView(context);
        initEmptyView(context);
        dismissAllView();
    }

    private void initLoadingView(Context context) {
        loadingView = new ChildView(context);
        loadingView.setImageResource(R.mipmap.ic_loading);
        loadingView.doAnimation();
        loadingView.setText("加载中");
        addView(loadingView);
    }

    private void initErrorView(Context context) {
        errorView = new ChildView(context);
        errorView.setImageResource(R.mipmap.ic_error);
        errorView.setText("加载失败");
        addView(errorView);
    }

    private void initEmptyView(Context context) {
        emptyView = new ChildView(context);
        emptyView.setImageResource(R.mipmap.ic_empty);
        emptyView.setText("空空如也");
        addView(emptyView);
    }

    public void addContentView(View view) {
        this.contentView = view;
        addView(contentView);
    }

    public void showLoadingView() {
        dismissAllView();
        loadingView.setVisibility(VISIBLE);
    }

    public void showErrorView() {
        dismissAllView();
        errorView.setVisibility(VISIBLE);
    }

    public void showEmptyView() {
        dismissAllView();
        emptyView.setVisibility(VISIBLE);
    }

    public void showContentView() {
        dismissAllView();
        if (contentView != null) {
            contentView.setVisibility(VISIBLE);
        }
    }

    private void dismissAllView() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(GONE);
        }
    }

    private class ChildView extends LinearLayout {
        private ImageView imageView;
        private TextView textView;

        public ChildView(Context context) {
            super(context);
            setGravity(Gravity.CENTER);
            setOrientation(LinearLayout.VERTICAL);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            setLayoutParams(params);
            initChild(context);
        }

        private void initChild(Context context) {
            imageView = new ImageView(context);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params1);
            addView(imageView);

            textView = new TextView(context);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params2.setMargins(0, 20, 0, 0);
            textView.setTextSize(16);
            textView.setLayoutParams(params2);
            addView(textView);
        }

        public void setImageResource(@DrawableRes int id) {
            imageView.setImageResource(id);
        }

        public void doAnimation() {
            doAnimation(null);
        }

        public void doAnimation(Animation animation) {
            if (animation == null) {
                animation = new RotateAnimation(
                        0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setRepeatMode(Animation.RESTART);
                animation.setRepeatCount(Animation.INFINITE);
                animation.setDuration(1000);
                animation.setInterpolator(new LinearInterpolator());
            }
            imageView.startAnimation(animation);
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}
