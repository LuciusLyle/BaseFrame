package com.luciuslyle.frame.base;

import android.content.Context;
import android.util.Log;

import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

//import io.reactivex.disposables.CompositeDisposable;
//import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends IBaseView, M extends BaseModel> implements IBasePresenter {

    private SoftReference<IBaseView> mReferenceView;
    private V mProxyView;
    private M mModel;
    /**
     * 管理事件流订阅的生命周期CompositeDisposable
     */
//    private CompositeDisposable compositeDisposable;
    
    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Override
    public void attach(IBaseView view) {

        //使用软引用创建对象
        mReferenceView = new SoftReference<>(view);
        //使用动态代理做统一的逻辑判断 aop 思想
        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (mReferenceView == null || mReferenceView.get() == null) {
                    return null;
                }
                return method.invoke(mReferenceView.get(), objects);
                
            }
        });

        //通过获得泛型类的父类，拿到泛型的接口类实例，通过反射来实例化 model
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (type != null) {
            Type[] types = type.getActualTypeArguments();
            try {
                mModel = (M) ((Class<?>) types[1]).newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }
//    public void addDisposable(Disposable disposable) {
//        if (disposable!=null){
//            if (compositeDisposable == null){
//                compositeDisposable = new CompositeDisposable();
//            }
//            compositeDisposable.add(disposable);
//        }
//    }    
    @Override
    public void detach() {
//        if (compositeDisposable != null) {
//            compositeDisposable.clear();
//            compositeDisposable = null;
//        }

        mReferenceView.clear();
        mReferenceView = null;
    }
    @SuppressWarnings("unchecked")
    public V getView() {
        return (V) mProxyView;
    }

    protected M getModel() {
        return mModel;
    }


}
