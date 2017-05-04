package com.winsion.multiplestatuslayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wyl on 2017/5/4.
 */

public abstract class BaseFragment extends Fragment {
    private MultipleStatusLayout mContentView;
    public LayoutInflater mInflater;
    public View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        if (mContentView == null) {
            mContentView = new MultipleStatusLayout(getContext());
            mView = setContentView();
            mContentView.addContentView(mView);
            init();
        }
        ViewGroup parent = (ViewGroup) mContentView.getParent();
        if (parent != null) {
            parent.removeView(mContentView);
        }
        return mContentView;
    }

    protected abstract View setContentView();

    public abstract void init();

    public void showLoadingView() {
        mContentView.showLoadingView();
    }

    public void showErrorView() {
        mContentView.showErrorView();
    }

    public void showEmptyView() {
        mContentView.showEmptyView();
    }

    public void showContentView() {
        mContentView.showContentView();
    }
}
