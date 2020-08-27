package com.luciuslyle.frame.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.luciuslyle.frame.R;
import com.luciuslyle.frame.base.BaseFragment;
import com.luciuslyle.frame.inject.InjectPresenter;

import androidx.annotation.Nullable;

public class SecondFragment extends BaseFragment implements SecondContract.ISecondView , MainContract.IMainView{

    private Button tvFragment;
    
    //多个Presenter 注解
    @InjectPresenter
    private SecondPresenter mPresenter;
    @InjectPresenter
    private MainPresenter mPresenter1;
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        tvFragment = findId(R.id.tv_fragment);
        tvFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter1.handlerData();
                mPresenter.handlerData();
            }
        });
    }


    @Override
    public void showDialog() {
        Log.e("xxx","显示进度");
//        Toast.makeText(getContext(), "this is Fragment", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void succes(final String content) {
        Log.e("xxx","activity 刷新数据");

    }

}
