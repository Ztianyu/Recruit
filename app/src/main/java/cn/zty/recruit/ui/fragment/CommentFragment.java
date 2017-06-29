package cn.zty.recruit.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.zty.baselib.utils.ResourceUtil;
import cn.zty.recruit.R;
import cn.zty.recruit.listener.SendReplayListener;

/**
 * Created by zty on 2016/12/19.
 */

public class CommentFragment extends DialogFragment {

    @BindView(R.id.editComment)
    EditText editComment;
    @BindView(R.id.btnSend)
    Button btnSend;
    Unbinder unbinder;

    private SendReplayListener listener;
    private String forumId;
    private String userId;
    private int position;

    public static CommentFragment newInstance(String forumId, int position) {
        CommentFragment fragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("forumId", forumId);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.view_comment);
        unbinder = ButterKnife.bind(this, dialog);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消


        editComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    btnSend.setBackgroundResource(R.drawable.btn_can_send);
                    btnSend.setTextColor(ResourceUtil.resToColor(getActivity(), R.color.white));
                    btnSend.setClickable(true);
                } else {
                    btnSend.setBackgroundResource(R.drawable.btn_can_not_send);
                    btnSend.setTextColor(ResourceUtil.resToColor(getActivity(), R.color.gray));
                    btnSend.setClickable(false);
                }
            }
        });
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0;
        windowParams.gravity = Gravity.BOTTOM;
        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        windowParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(windowParams);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSend)
    public void onClick() {
        dismiss();
        listener.onSend(forumId, userId, position, editComment.getText().toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SendReplayListener) context;
        } catch (ClassCastException e) {
            dismiss();
        }
    }
}
