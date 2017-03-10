package cn.zty.linkage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

import cn.zty.linkage.adapter.LeftMenuBaseListAdapter;
import cn.zty.linkage.adapter.RightMenuBaseListAdapter;
import cn.zty.linkage.bean.BaseMenuBean;
import cn.zty.linkage.model.ILinkage;


/**
 * Created by zz on 2016/8/19.
 */
public class LinkageView<T extends BaseMenuBean> extends LinearLayout implements ILinkage<T> {

    private View rootView;
    private RelativeLayout customNoDataView;
    private ListView lvLeft;
    private ListView lvRight;
    private LeftMenuBaseListAdapter leftAdapter;
    private RightMenuBaseListAdapter rightAdapter;

    private List<T> list;
    private OnItemClickListener mItemClickListener;

    public LinkageView(Context context) {
        super(context);
        init(context, null);
    }

    public LinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LinkageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        rootView = LayoutInflater.from(context).inflate(R.layout.view_linkage, null);
        rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(rootView);

        customNoDataView = (RelativeLayout) rootView.findViewById(R.id.custom_no_data_view);

        lvLeft = (ListView) rootView.findViewById(R.id.left_lv);
        lvRight = (ListView) rootView.findViewById(R.id.right_lv);

        setItemClickListener();

        if (attrs == null) {

        } else {

        }
    }



    @Override
    public void setLeftMenuAdapter(LeftMenuBaseListAdapter adapter) {
        leftAdapter = adapter;
        if (leftAdapter != null)
            lvLeft.setAdapter(leftAdapter);
    }

    @Override
    public void setRightContentAdapter(RightMenuBaseListAdapter adapter) {
        rightAdapter = adapter;
        if (rightAdapter != null)
            lvRight.setAdapter(rightAdapter);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public void updateData(List<T> list) {
        this.list = list;
        if (leftAdapter != null) {
            leftAdapter.setList(this.list);
            if (this.list != null && this.list.size() > 0) {
                leftAdapter.setSelection(0);
                if (mItemClickListener != null) {
                    mItemClickListener.onLeftClick(null, 0);
                }
            }
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    @Override
    public void setCustomNoDataView(View view) {
        if (view != null) {
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT));
            this.customNoDataView.removeAllViews();
            this.customNoDataView.addView(view);
        }
    }

    @Override
    public void setCustomNoDataViewWithLayoutId(int layoutId) {
        if (layoutId > 0) {
            View view = LayoutInflater.from(getContext()).inflate(layoutId, null);
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT));
            this.customNoDataView.removeAllViews();
            this.customNoDataView.addView(view);
        }
    }

    private void setItemClickListener() {
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (leftAdapter != null) {
                    leftAdapter.setSelection(i);
                }
                if (mItemClickListener != null) {
                    mItemClickListener.onLeftClick(view, i);
                }
            }
        });
        lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mItemClickListener != null && i < rightAdapter.getCount()) {
                    mItemClickListener.onRightClick(view, i);
                }
            }
        });
    }


}
