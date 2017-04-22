package cn.zty.recruit.ui.fragment;

import android.app.Dialog;
import android.graphics.Color;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.DictAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.base.RecruitApplication;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.AreaSelectListener;
import cn.zty.recruit.presenter.GetCityPresenter;
import cn.zty.recruit.presenter.GetProvincePresenter;
import cn.zty.recruit.view.AreaView;

/**
 * Created by zty on 2017/3/15.
 */

public class AreaSelectFragment extends DialogFragment implements AreaView {

    @BindView(R.id.areaList)
    ListView areaList;
    @BindView(R.id.viewSelectBottom)
    View viewSelectBottom;

    Unbinder unbinder;

    private int height;
    private int type;//0:省；1：市
    private DictAdapter dictAdapter;

    private AreaSelectListener listener;

    private GetProvincePresenter getProvincePresenter;
    private GetCityPresenter getCityPresenter;

    private String provinceId;

    public static AreaSelectFragment newInstance(int height, int type, @Nullable String provinceId, AreaSelectListener listener) {
        AreaSelectFragment fragment = new AreaSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        bundle.putInt("type", type);
        bundle.putString("provinceId", provinceId);
        fragment.setListener(listener);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");
        type = getArguments().getInt("type");
        provinceId = getArguments().getString("provinceId");

        getProvincePresenter = new GetProvincePresenter();
        getProvincePresenter.attach(this);

        getCityPresenter = new GetCityPresenter();
        getCityPresenter.attach(this);
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

        dictAdapter = new DictAdapter(getActivity(), type);
        areaList.setAdapter(dictAdapter);

        areaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                listener.onAreaSelect(dictAdapter.getData().get(position).getKey(),
                        dictAdapter.getData().get(position).getValue(), type);
            }
        });

        viewSelectBottom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });

        if (type == 0) {
            getProvincePresenter.getProvince();
        } else {
            getCityPresenter.getCity(provinceId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        getCityPresenter.detach();
        getProvincePresenter.detach();
    }

    public AreaSelectListener getListener() {
        return listener;
    }

    public void setListener(AreaSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAreaSuccess(int type, List<TipModel> models) {
        if (type == 0) {
            TipModel tipModel = new TipModel();
            tipModel.setKey("-1");
            tipModel.setValue("全部");
            dictAdapter.addListAtEnd(tipModel);
        }
        dictAdapter.addListAtEnd(models);
    }
}
