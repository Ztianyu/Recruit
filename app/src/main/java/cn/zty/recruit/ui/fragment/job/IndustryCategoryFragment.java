package cn.zty.recruit.ui.fragment.job;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.DictAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.Constants;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.IndustrySelectListener;
import cn.zty.recruit.presenter.DictPresenter;
import cn.zty.recruit.view.DictListView;

/**
 * Created by zty on 2017/6/1.
 */

public class IndustryCategoryFragment extends DialogFragment implements DictListView {

    @BindView(R.id.areaList)
    ListView areaList;
    @BindView(R.id.viewSelectBottom)
    View viewSelectBottom;

    Unbinder unbinder;

    private int height;

    private IndustrySelectListener listener;

    private DictAdapter dictAdapter;

    private DictPresenter presenter;

    private static List<TipModel> industryModels;

    public static IndustryCategoryFragment newInstance(int height, IndustrySelectListener listener) {
        IndustryCategoryFragment fragment = new IndustryCategoryFragment();
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

        presenter = new DictPresenter();
        presenter.attach(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_list);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        unbinder = ButterKnife.bind(this, dialog);
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
        windowParams.height = BaseActivity.screenHeight - height - RecruitApplication.getInstance().getStatusBarHeight();
        window.setAttributes(windowParams);
        window.setBackgroundDrawable(new ColorDrawable(ResourceUtil.resToColor(getActivity(), R.color.transparent60)));

        dictAdapter = new DictAdapter(getActivity(), -1);
        areaList.setAdapter(dictAdapter);

        areaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                listener.onIndustrySelect(dictAdapter.getData().get(position).getKey(),
                        dictAdapter.getData().get(position).getValue());
            }
        });

        viewSelectBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });

        if (industryModels != null) {
            dictAdapter.setData(industryModels);
        } else {
            presenter.getDictList(Constants.DICT_TYPE9);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.detach();
    }

    public IndustrySelectListener getListener() {
        return listener;
    }

    public void setListener(IndustrySelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onDictSuccess(String type, List<TipModel> models) {
        if (industryModels == null)
            industryModels = new ArrayList<>();
        industryModels.clear();
        TipModel tipModel = new TipModel();
        tipModel.setKey("-1");
        tipModel.setValue("全部");
        industryModels.add(tipModel);
        industryModels.addAll(models);
        dictAdapter.setData(industryModels);
    }
}
