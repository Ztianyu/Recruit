package cn.zty.recruit.ui.fragment.school;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zty.linkage.LinkageView;
import cn.zty.linkage.model.ILinkage;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.LeftMenuListAdapter;
import cn.zty.recruit.adapter.RightMajorListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.bean.MajorModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.MajorSelectListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.presenter.HotMajorPresenter;
import cn.zty.recruit.view.DictListView;
import cn.zty.recruit.view.HotMajorView;

/**
 * 选择专业
 * Created by zty on 2017/3/9.
 */

public class MajorSelectFragment extends DialogFragment implements
        DictListView,
        HotMajorView {

    int height;
    LeftMenuListAdapter leftMenuListAdapter;
    RightMajorListAdapter rightMajorListAdapter;
    @BindView(R.id.linkage)
    LinkageView linkage;

    private MajorSelectListener listener;

    private DictPresenter dictPresenter;
    private HotMajorPresenter hotMajorPresenter;

    private String discipline;//学科门类id

    public static MajorSelectFragment newInstance(int height, MajorSelectListener listener) {
        MajorSelectFragment fragment = new MajorSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        fragment.setArguments(bundle);
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");

        dictPresenter = new DictPresenter();
        dictPresenter.attach(this);

        hotMajorPresenter = new HotMajorPresenter();
        hotMajorPresenter.attach(this);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_major_select);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        ButterKnife.bind(this, dialog);
        return dialog;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.y = height;
        windowParams.dimAmount = 0;
        windowParams.gravity = Gravity.TOP;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = (int) (BaseActivity.screenHeight * 0.6);
        window.setAttributes(windowParams);

        leftMenuListAdapter = new LeftMenuListAdapter(getActivity(), new ArrayList<TipModel>());
        linkage.setLeftMenuAdapter(leftMenuListAdapter);

        rightMajorListAdapter = new RightMajorListAdapter(getActivity(), new ArrayList<MajorModel>());
        linkage.setRightContentAdapter(rightMajorListAdapter);

        linkage.setOnItemClickListener(new ILinkage.OnItemClickListener() {
            @Override
            public void onLeftClick(View itemView, int position) {
                discipline = leftMenuListAdapter.getList().get(position).getKey();
                hotMajorPresenter.getHotMajorList(0, discipline, 1, 100);
            }

            @Override
            public void onRightClick(View itemView, int position) {
                dismiss();
                listener.onMajorSelect(rightMajorListAdapter.getList().get(position));
            }
        });

        dictPresenter.getDictList(Constants.DICT_TYPE1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dictPresenter.detach();
        hotMajorPresenter.detach();
    }

    public MajorSelectListener getListener() {
        return listener;
    }

    public void setListener(MajorSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onHotMajorSuccess(List<MajorModel> majorModels) {
        rightMajorListAdapter.setList(majorModels);
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        linkage.updateData(models);
    }
}
