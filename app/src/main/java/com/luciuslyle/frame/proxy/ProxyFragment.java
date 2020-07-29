package com.luciuslyle.frame.proxy;


import com.luciuslyle.frame.base.IBaseView;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
