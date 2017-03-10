package cn.zty.recruit.ui.fragment.school;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import cn.zty.linkage.LinkageView;
import cn.zty.linkage.model.ILinkage;
import cn.zty.recruit.R;
import cn.zty.recruit.adapter.LeftMenuListAdapter;
import cn.zty.recruit.adapter.RightContentListAdapter;
import cn.zty.recruit.base.BaseActivity;
import cn.zty.recruit.bean.LeftMenuEntity;
import cn.zty.recruit.bean.RightContentEntity;

/**
 * 选择专业
 * Created by zty on 2017/3/9.
 */

public class MajorSelectFragment extends DialogFragment {

    int height;
    LeftMenuListAdapter leftMenuListAdapter;
    RightContentListAdapter rightContentListAdapter;
    @BindView(R.id.linkage)
    LinkageView linkage;

    public static MajorSelectFragment newInstance(int height) {
        MajorSelectFragment fragment = new MajorSelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("height", height);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        height = getArguments().getInt("height");
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

        leftMenuListAdapter = new LeftMenuListAdapter(getActivity(), new ArrayList<LeftMenuEntity>());
        linkage.setLeftMenuAdapter(leftMenuListAdapter);

        rightContentListAdapter = new RightContentListAdapter(getActivity(), new ArrayList<RightContentEntity>());
        linkage.setRightContentAdapter(rightContentListAdapter);

        linkage.setOnItemClickListener(new ILinkage.OnItemClickListener() {
            @Override
            public void onLeftClick(View itemView, int position) {
                List<RightContentEntity> rightContentEntities = new ArrayList<>();
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());
                rightContentEntities.add(new RightContentEntity());

                rightContentListAdapter.setList(rightContentEntities);
            }

            @Override
            public void onRightClick(View itemView, int position) {

            }
        });


        List<LeftMenuEntity> leftMenuEntities = new ArrayList<>();
        leftMenuEntities.add(new LeftMenuEntity());
        leftMenuEntities.add(new LeftMenuEntity());
        leftMenuEntities.add(new LeftMenuEntity());
        linkage.updateData(leftMenuEntities);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
