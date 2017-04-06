package cn.zty.recruit.ui.fragment.learn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.EnrollTypeAdapter;
import cn.zty.recruit.bean.DepositSystemModel;
import cn.zty.recruit.listener.EnrollTypeSelectListener;
import cn.zty.recruit.presenter.DepositSystemPresenter;
import cn.zty.recruit.view.DepositSystemView;

/**
 * 报名定金
 * Created by zty on 2017/3/17.
 */

public class EnrollTypeFragment extends DialogFragment implements DepositSystemView {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    private EnrollTypeAdapter adapter;

    private EnrollTypeSelectListener listener;
    private String office;

    private DepositSystemPresenter presenter;

    public static EnrollTypeFragment newInstance(EnrollTypeSelectListener listener, String office) {
        EnrollTypeFragment fragment = new EnrollTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("office", office);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.x_recycler, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.6f;
        windowParams.gravity = Gravity.CENTER;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        office = getArguments().getString("office");

        presenter = new DepositSystemPresenter();
        presenter.attach(this);

        adapter = new EnrollTypeAdapter(getContext(), listener);

        recyclerView.verticalLayoutManager(getContext())
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        presenter.getDepositSystemList(office);
    }

    @Override
    public void onDepositSystemSuccess(List<DepositSystemModel> models) {
        adapter.setData(models);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    public EnrollTypeSelectListener getListener() {
        return listener;
    }

    public void setListener(EnrollTypeSelectListener listener) {
        this.listener = listener;
    }

}
