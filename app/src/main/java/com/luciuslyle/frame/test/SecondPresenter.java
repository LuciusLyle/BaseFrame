package com.luciuslyle.frame.test;


import android.util.Log;

import com.luciuslyle.frame.test.SecondContract;
import com.luciuslyle.frame.base.BasePresenter;
import com.luciuslyle.frame.test.Callback;
import com.luciuslyle.frame.test.SecondModel;


public class SecondPresenter extends BasePresenter<SecondContract.ISecondView, SecondModel> implements SecondContract.ISecondPresenter {

    @Override
    public void handlerData() {
        getView().showDialog();
        getModel().requestBaidu(new Callback() {
            @Override
            public void onError() {
                if (getView()!=null) {
                    getView().succes("SecondPresenter 回调");
                }
               
            }
        });
    }

 

    @Override
    public void detach() {
        super.detach();
        
        Log.e("xxx","关闭:SecondPresenter");
    }
}
