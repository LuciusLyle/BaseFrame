package com.luciuslyle.frame.base;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.luciuslyle.frame.proxy.ProxyFragment;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 
 *  懒加载使用： 
 *  1:  add+show+hide 模式下的懒加载实现
 *     private Fragment mCurrentFragment;
 *     private void switchFrament( Fragment to) {
 *         FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
 *         if (to == null || to == mCurrentFragment)
 *             return;
 *         if (mCurrentFragment != null) {
 *             ft.hide(mCurrentFragment);
 *         }
 *         if (!to.isAdded()) {
 *             ft.add(R.id.tv, to).setMaxLifecycle(to, Lifecycle.State.RESUMED).commit();
 *
 *         } else {
 *             ft.show(to).setMaxLifecycle(to, Lifecycle.State.STARTED).commit();
 *         }
 *         mCurrentFragment = to;
 *     }
 *     
 *  2:  ViewPager + Fragment 模式下的懒加载实现
 *  @Deprecated
 *   public FragmentPagerAdapter(@NonNull FragmentManager fm) {
 *         this(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
 *   }
 *
 */

public abstract class BaseFragment extends Fragment implements IBaseView {

    private ProxyFragment mProxyFragment;
    public View rootView;
    private boolean isLoaded = false;

    protected abstract @LayoutRes
    int setLayout();

    protected abstract void initViews(@Nullable Bundle savedInstanceState);
    protected void lazyInit(){
    }

    @SuppressWarnings("ConstantConditions")
    protected <T extends View> T findId(@IdRes int viewId) {
        return this.getView().findViewById(viewId);
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(setLayout(), container, false);
            //rootView = inflater.inflate(layoutId, null);
            //initView(rootView);// 控件初始化
        }
        //View rootView = inflater.inflate(setLayout(), container, false);
        mProxyFragment = createProxyFragment();
        mProxyFragment.bindPresenter();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isLoaded && !isHidden()) {
            isLoaded = true;
            lazyInit();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    private ProxyFragment createProxyFragment() {
        if (mProxyFragment == null) {
            return new ProxyFragment<>(this);
        }
        return mProxyFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoaded = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxyFragment.unbindPresenter();
    }
}
