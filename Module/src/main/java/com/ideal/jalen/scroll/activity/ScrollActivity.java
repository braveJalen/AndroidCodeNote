package com.ideal.jalen.scroll.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ideal.jalen.R;
import com.ideal.jalen.base.BaseActivity;

/**
 * author: Jalen
 * date: 2017/6/23. 17:44
 * describe:
 */
public class ScrollActivity extends BaseActivity {
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_scroll;
    }

    @Override
    public void initUI(@Nullable Bundle savedInstanceState) {

    }
}
