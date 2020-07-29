package com.luciuslyle.frame.proxy;

import com.luciuslyle.frame.base.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
