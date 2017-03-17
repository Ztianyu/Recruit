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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.EnrollTypeAdapter;
import cn.zty.recruit.bean.EnrollTypeModel;
import cn.zty.recruit.listener.EnrollTypeSelectListener;

/**
 * Created by zty on 2017/3/17.
 */

public class EnrollTypeFragment extends DialogFragment {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    private EnrollTypeAdapter adapter;

    private EnrollTypeSelectListener listener;

    public static EnrollTypeFragment newInstance(EnrollTypeSelectListener listener) {
        EnrollTypeFragment fragment = new EnrollTypeFragment();
        fragment.setListener(listener);
        return fragment;
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

        adapter = new EnrollTypeAdapter(getContext(), listener);

        recyclerView.verticalLayoutManager(getContext())
                .setAdapter(adapter);
        recyclerView.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        List<EnrollTypeModel> list = new ArrayList<>();
        list.add(new EnrollTypeModel());
        list.add(new EnrollTypeModel());
        adapter.setData(list);
    }

    public EnrollTypeSelectListener getListener() {
        return listener;
    }

    public void setListener(EnrollTypeSelectListener listener) {
        this.listener = listener;
    }
}
