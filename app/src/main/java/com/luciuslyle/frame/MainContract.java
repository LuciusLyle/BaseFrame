package com.luciuslyle.frame;

import com.luciuslyle.frame.base.IBasePresenter;
import com.luciuslyle.frame.base.IBaseView;

/**
 * 接口
 * M、V、P 三层提供的接口访问与回调
 */
public interface MainContract {
    interface IMainModel {
        void requestBaidu();
    }

    interface IMainView extends IBaseView {
        void showDialog();

        void succes(String content);
    }

    interface IMainPresenter extends IBasePresenter {
        void handlerData();
    }
}
