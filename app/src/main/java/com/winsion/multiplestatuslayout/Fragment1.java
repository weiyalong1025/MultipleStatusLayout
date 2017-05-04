package com.winsion.multiplestatuslayout;

import android.view.View;

/**
 * Created by wyl on 2017/5/4.
 */

public class Fragment1 extends BaseFragment {

    @Override
    protected View setContentView() {
        return mInflater.inflate(R.layout.fragment_1, null);
    }

    @Override
    public void init() {
        showEmptyView();
    }
}
