package cn.zty.recruit.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zty.baselib.presenter.IBasePresenter;

/**
 * Created by zty on 2017/2/21.
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected Context context;
    protected View mRootView;
    protected Unbinder unbinder;

    protected List<IBasePresenter> presenters;

    protected abstract int initLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        presenters = new ArrayList<>();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLayoutId();
        mRootView = inflater.inflate(initLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();

        if (presenters.size() > 0) {
            for (IBasePresenter presenter : presenters) {
                presenter.detach();
            }
        }
        super.onDestroyView();
    }
}
