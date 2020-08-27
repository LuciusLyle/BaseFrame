package com.luciuslyle.frame.test;

import android.util.Log;


import com.luciuslyle.frame.test.MainContract;
import com.luciuslyle.frame.base.BasePresenter;
import com.luciuslyle.frame.test.DataModel;
//import com.luciuslyle.net.demo.UserService;
//import com.luciuslyle.net.http.RetrofitHelper;
//import com.luciuslyle.net.http.RetrofitServiceManager;
//import com.luciuslyle.net.http.call.RequestCallback;



/**
 * presenter 
 */
public class MainPresenter extends BasePresenter<MainContract.IMainView, DataModel> implements MainContract.IMainPresenter {

    @Override
    public void handlerData() {
        getView().showDialog();
        getModel().requestBaidu();
        Log.e("xxx","MainPresenter ");
//        RetrofitHelper.qurest(RetrofitServiceManager.getInstance().obtainRetrofitService(UserService.class).executeGet(""), new RequestCallback<ResponseBody>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                super.onResponse(response);
//            }
//            
//        });
    }


    @Override
    public void detach() {
        super.detach();
        /**
         * 释放内存、关闭网络请求、关闭线程等操作
         */
        Log.e("xxx", "detech: 解除绑定，释放内存");
    }
}
