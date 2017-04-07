package cn.zty.recruit.ui.fragment.learn;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.IndustryTypeAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.IndustryTypeModel;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.IndustryTypeListener;
import cn.zty.recruit.presenter.IndustryTypePresenter;
import cn.zty.recruit.view.IndustryTypeView;

/**
 * 培训机构行业类型列表
 * Created by zty on 2017/3/28.
 */

public class IndustryTypeFragment extends DialogFragment implements IndustryTypeView {
    @BindView(R.id.areaList)
    ListView areaList;

    Unbinder unbinder;

    private int height;
    private IndustryTypeAdapter adapter;

    private IndustryTypeListener listener;

    private IndustryTypePresenter presenter;

    public static IndustryTypeFragment newInstance(int height, IndustryTypeListener listener) {
        IndustryTypeFragment fragment = new IndustryTypeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        fragment.setListener(listener);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");

        presenter = new IndustryTypePresenter();
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
        windowParams.height = (int) (BaseActivity.screenHeight * 0.6);
        window.setAttributes(windowParams);

        adapter = new IndustryTypeAdapter(getActivity());
        areaList.setAdapter(adapter);

        areaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                listener.onIndustryTypeSelect(adapter.getData().get(position).getId(),
                        adapter.getData().get(position).getName());
            }
        });

        presenter.getIndustryTypeList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.detach();
    }

    public IndustryTypeListener getListener() {
        return listener;
    }

    public void setListener(IndustryTypeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onIndustryTypeSuccess(List<IndustryTypeModel> models) {

        IndustryTypeModel tipModel = new IndustryTypeModel();
        tipModel.setId("-1");
        tipModel.setName("全部");
        adapter.addListAtEnd(tipModel);
        adapter.addListAtEnd(models);
    }
}
