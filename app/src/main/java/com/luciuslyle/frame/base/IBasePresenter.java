package com.luciuslyle.frame.base;


public interface IBasePresenter {
    void attach(IBaseView view);

    void detach();
}
