package cn.zty.recruit.ui.fragment.learn;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.DictAdapter;
import cn.zty.recruit.bean.TipModel;
import cn.zty.recruit.listener.AreaSelectListener;

/**
 * Created by zty on 2017/3/16.
 */

public class LearnProjectFragment extends DialogFragment {
    @BindView(R.id.areaList)
    ListView areaList;

    private int height;
    private int type;//0:省；1：市:2:项目分类
    private DictAdapter dictAdapter;

    private AreaSelectListener listener;

    public static LearnProjectFragment newInstance(int height, int type) {
        LearnProjectFragment fragment = new LearnProjectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");
        type = getArguments().getInt("type");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_list);
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
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);

        dictAdapter = new DictAdapter(getActivity(), type);
        areaList.setAdapter(dictAdapter);
        List<TipModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new TipModel());
        }
        dictAdapter.setData(list);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AreaSelectListener) context;
        } catch (ClassCastException e) {
            dismiss();
        }
    }
}
