package cn.zty.recruit.ui.fragment;

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
import butterknife.Unbinder;
import cn.droidlover.xrecyclerview.XRecyclerView;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.EducationSelectAdapter;
import cn.zty.recruit.base.BaseData;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.EducationSelectListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.view.DictListView;

/**
 * Created by zty on 2017/3/17.
 */

public class SelectEducation extends DialogFragment implements DictListView {

    @BindView(R.id.recyclerViewSelect)
    XRecyclerView recyclerViewSelect;
    Unbinder unbind;

    private DictPresenter presenter;

    private EducationSelectAdapter adapter;

    private EducationSelectListener listener;

    private String education;

    public static SelectEducation newInstance(String education, EducationSelectListener listener) {
        SelectEducation fragment = new SelectEducation();
        Bundle bundle = new Bundle();
        bundle.putString("education", education);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_education_select, null);
        unbind = ButterKnife.bind(this, view);
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

        education = getArguments().getString("education");

        adapter = new EducationSelectAdapter(getActivity(), listener);

        recyclerViewSelect.verticalLayoutManager(getContext())
                .setAdapter(adapter);
        recyclerViewSelect.horizontalDivider(R.color.colorDiver, R.dimen.diverHeight);

        presenter = new DictPresenter();
        presenter.attach(this);

        if (BaseData.educations.size() <= 0) {
            presenter.getDictList(Constants.DICT_TYPE5);
        } else {
            for (TipModel tipModel : BaseData.educations) {
                if (tipModel.getValue().equals(education)) {
                    tipModel.setSelected(true);
                } else {
                    tipModel.setSelected(false);
                }
            }
            adapter.setData(BaseData.educations);
        }
    }

    public EducationSelectListener getListener() {
        return listener;
    }

    public void setListener(EducationSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {

        BaseData.educations.clear();
        BaseData.educations.addAll(models);

        for (TipModel tipModel : BaseData.educations) {
            if (tipModel.getValue().equals(education)) {
                tipModel.setSelected(true);
            } else {
                tipModel.setSelected(false);
            }
        }
        adapter.setData(BaseData.educations);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbind.unbind();
        presenter.detach();
    }
}
