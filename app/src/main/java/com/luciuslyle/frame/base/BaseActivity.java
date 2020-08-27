package com.luciuslyle.frame.base;

import android.content.Context;
import android.os.Bundle;

import android.view.View;

import com.luciuslyle.frame.proxy.ProxyActivity;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private ProxyActivity mProxyActivity;
    
    

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    protected abstract void initData();


    protected <T extends View> T findId(@IdRes int viewId) {
        return findViewById(viewId);
    }

    //https://www.jianshu.com/p/d60d6027ef0b
    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxyActivity = createProxyActivity();
        mProxyActivity.bindPresenter();
        initViews(savedInstanceState);
        initData();
    }

    @SuppressWarnings("unchecked")
    private ProxyActivity createProxyActivity() {
        if (mProxyActivity == null) {
            return new ProxyActivity(this);
        }
        return mProxyActivity;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxyActivity.unbindPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
