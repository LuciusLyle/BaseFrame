package com.luciuslyle.frame;


import com.luciuslyle.frame.base.IBasePresenter;
import com.luciuslyle.frame.base.IBaseView;
import com.luciuslyle.frame.model.Callback;

public interface SecondContract {
    interface ISecondModel {
        void requestBaidu(Callback callback);
    }

    interface ISecondView extends IBaseView {
        void showDialog();

        void succes(String content);
    }

    interface ISecondPresenter extends IBasePresenter {
        void handlerData();
    }
}
