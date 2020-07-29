package com.luciuslyle.frame.model;


import android.util.Log;

import com.luciuslyle.frame.SecondContract;
import com.luciuslyle.frame.base.BaseModel;

public class SecondModel extends BaseModel implements SecondContract.ISecondModel {

    @Override
    public void requestBaidu(final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("xxx","Model加载数据");
                    Thread.sleep(3000);
                    callback.onError();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    
                }
            }   
        }).start();
    }
}
