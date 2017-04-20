package cn.zty.recruit.adapter;

import android.content.Context;
import android.view.ViewGroup;

import cn.droidlover.xrecyclerview.RecyclerAdapter;
import cn.zty.baselib.holder.ViewHolder;
import cn.zty.baselib.utils.MyImageLoader;
import cn.zty.baselib.utils.MyTextUtils;
import cn.zty.baselib.utils.TimeUtils;
import cn.zty.recruit.R;
import cn.zty.recruit.bean.UserModel;

/**
 * Created by zty on 2017/4/20.
 */

public class InviteUserAdapter extends RecyclerAdapter<UserModel, ViewHolder> {

    public InviteUserAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(context, R.layout.item_invite_user, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setImage(context, R.id.imgInviteHeader, data.get(position).getPhoto());
        holder.setText(R.id.textInviteName, MyTextUtils.notNullStr(data.get(position).getFullNm()));
        holder.setText(R.id.textInviteSex, MyTextUtils.notNullStr(data.get(position).getSex()));
        holder.setText(R.id.textInvitePhone, data.get(position).getMobile());
        holder.setText(R.id.textInviteTime,
                TimeUtils.getChatTime(TimeUtils.dateToStamp(data.get(position).getCreateDate() + ":00")));
    }
}
