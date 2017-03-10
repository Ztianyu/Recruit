package cn.zty.baselib.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {


    public Context context;

    public List<T> mData;


    public MyBaseAdapter(Context context) {
        super();
        this.context = context;
        this.mData = new ArrayList<>();
    }


    public MyBaseAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 设置数据源
     *
     * @param data
     */
    public void setData(List<T> data) {
        this.mData.clear();
        if (data != null) {
            this.mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加数据列表到列表尾部
     */
    public void addListAtEnd(List<T> list) {
        this.mData.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加数据列表到列表尾部
     */
    public void addListAtEnd(T data) {
        this.mData.add(data);
        notifyDataSetChanged();
    }


}
