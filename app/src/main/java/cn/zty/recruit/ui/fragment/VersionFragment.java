package cn.zty.recruit.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.VersionModel;
import cn.zty.recruit.utils.DownloadUtils;
import cn.zty.recruit.utils.LengthUtils;

/**
 * Created by zty on 2017/4/13.
 */

public class VersionFragment extends DialogFragment {

    @BindView(R.id.textUpdateTitle)
    TextView textUpdateTitle;
    @BindView(R.id.textUpdateSize)
    TextView textUpdateSize;
    @BindView(R.id.textUpdateContent)
    TextView textUpdateContent;
    @BindView(R.id.textVersionCancel)
    TextView textVersionCancel;
    @BindView(R.id.textVersionSure)
    TextView textVersionSure;
    Unbinder unbinder;

    private String title;
    private VersionModel versionModel;

    public static VersionFragment newInstance(String title, VersionModel versionModel) {
        VersionFragment fragment = new VersionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putParcelable("versionModel", versionModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_version, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        title = getArguments().getString("title");
        versionModel = getArguments().getParcelable("versionModel");
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

        textUpdateTitle.setText(title);

        textUpdateContent.setText("更新内容\n\u3000" + versionModel.getRemarks().replace("&", "\n\u3000"));

        MyTask mTask = new MyTask();
        mTask.execute(versionModel.getAppAddress());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.textVersionCancel, R.id.textVersionSure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textVersionCancel:
                dismiss();
                break;
            case R.id.textVersionSure:
                DownloadUtils.downApk(getActivity(), versionModel.getAppAddress(), "recruit" + versionModel.getAppVersion());
                dismiss();
                break;
        }
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            long length = LengthUtils.getUrlLength(params[0]);
            return "新版本大小：" + String.format("%.2f", (double) length / 1024 / 1024) + "M";
        }

        @Override
        protected void onPostExecute(String s) {
            textUpdateSize.setText(s);
        }
    }
}
