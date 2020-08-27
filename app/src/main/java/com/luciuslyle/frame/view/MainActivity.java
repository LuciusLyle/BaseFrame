package com.luciuslyle.frame.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.luciuslyle.frame.MainContract;
import com.luciuslyle.frame.R;
import com.luciuslyle.frame.base.BaseActivity;
import com.luciuslyle.frame.inject.InjectPresenter;
import com.luciuslyle.frame.presenter.MainPresenter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;


public class MainActivity extends BaseActivity implements MainContract.IMainView {

    private Button tv;
    //Presenter 注解 （必须实现IMainView 不然报错）
    @InjectPresenter
    private MainPresenter mPresenter;
    
    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }


    Toast mToast;
    int mInt = 0;
    long oldTime=0;
    @Override
    protected void initViews() {
        tv = findId(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getContext(), SecondActivity.class));
            }
        });
    }


    @Override
    protected void initData() {
        mPresenter.handlerData();
    }
    ProgressDialog dialog;
    @Override
    public void showDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1500);
    }

    @Override
    public void succes(final String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "" + content, Toast.LENGTH_SHORT).show();
                tv.setText(content);
            }
        });
    }
}
