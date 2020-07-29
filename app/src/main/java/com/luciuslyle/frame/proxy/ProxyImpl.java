package com.luciuslyle.frame.proxy;


import com.luciuslyle.frame.base.BasePresenter;
import com.luciuslyle.frame.base.IBaseView;
import com.luciuslyle.frame.inject.InjectPresenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Presenter 与 view 的绑定和解绑 
 * 
 * 支持多 Presenter注解 绑定
 * 
 * 使用反射 获取注解类@InjectPresenter Presenter
 * 
 */
public class ProxyImpl implements IProxy {

    private IBaseView mView;
    private List<BasePresenter> mInjectPresenters;

    public ProxyImpl(IBaseView view) {
        this.mView = view;
        mInjectPresenters = new ArrayList<>();
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    public void bindPresenter() {
        //获得已经申明的变量，包括私有的
        Field[] fields = mView.getClass().getDeclaredFields();
        for (Field field : fields) {
            //获取自定义Presenter注解类型
            InjectPresenter injectPresenter = field.getAnnotation(InjectPresenter.class);
            if (injectPresenter != null) {
                try {
                    Class<? extends BasePresenter> type = (Class<? extends BasePresenter>) field.getType();
                    //静态方法实例化对象
                    BasePresenter mInjectPresenter = type.newInstance();
                    //绑定View
                    mInjectPresenter.attach(mView);
                    field.setAccessible(true);
                    field.set(mView, mInjectPresenter);
                    //多个Presenter
                    mInjectPresenters.add(mInjectPresenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassCastException e) {
                    e.printStackTrace();
                    throw new RuntimeException("SubClass must extends Class:BasePresenter");
                }
            }
        }
    }

    @Override
    public void unbindPresenter() {
        /**
         * 解绑，避免内存泄漏
         */
        for (BasePresenter presenter : mInjectPresenters) {
            presenter.detach();
        }
        mInjectPresenters.clear();
        mInjectPresenters = null;
    }
}
