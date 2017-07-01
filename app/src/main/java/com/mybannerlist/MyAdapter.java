package com.mybannerlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSH on 2017/7/1.
 * 根据不同的 ViewType 返回不同的 ViewHolder
 * 通过 setter 方法将不同的 View 注入进 Adapter
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final int TYPE_HEADER = 0;

    public static final int TYPE_NOMAL = 1;

    private View mHeaderView;

    private List<String> mDatas = new ArrayList<>();

    private OnItemClickListener mListener;

    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);//插入下标0位置
    }

    public void addDatas(List<String> datas){
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null){
            return TYPE_NOMAL;
        }
        if(position == 0){
            return TYPE_HEADER;
        }
        return TYPE_NOMAL;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            return new MyViewHolder(mHeaderView);
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) {
            return;
        }

        final int pos = getRealPosition(holder);
        final String data = mDatas.get(pos);


        holder.textView.setText(data);
        if(mListener == null) return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(pos, data);
            }
        });


    }

    /**
     * 添加头部布局后的位置
     * headerView 不为空则 position - 1
     * @param holder
     * @return
     */
    private int getRealPosition(MyViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        //header 不为空，则 rv 的 Count 需要 +1
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            if(itemView == mHeaderView){ return; }

            textView = (TextView) itemView.findViewById(R.id.tv_item);

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position, String data);
    }
}
