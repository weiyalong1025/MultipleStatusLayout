package com.winsion.multiplestatuslayout;

import android.view.View;

/**
 * Created by wyl on 2017/5/4.
 */

public class Fragment3 extends BaseFragment {
    @Override
    protected View setContentView() {
        return mInflater.inflate(R.layout.fragment_2, null);
    }

    @Override
    public void init() {
        showLoadingView();
    }
}
