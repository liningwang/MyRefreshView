package com.wangln.myrefreshview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/5 0005.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    static String[] text = {"afdf","fdfdd","dddd","ffff","afdf","fdfdd","dddd","ffff",
            "afdf","fdfdd","dddd","ffff",
            "afdf","fdfdd","dddd","ffff",
            "afdf","fdfdd","dddd","ffff"};
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        } else if(viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1, null);
        } else if(viewType == 3) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, null);
        }
        ViewHolder viewHolder = new ViewHolder(view,viewType);
        Log.d("wang","onCreateViewHolder " );
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return 1;
        }else if(position == text.length + 1) {
            return 3;
        }
        return 2;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Log.d("wang","onBindViewHolder " );
        if(position > 0 && position < text.length + 1) {
            holder.tv.setText(text[position - 1]);
        }
    }

    @Override
    public int getItemCount() {
        return text.length + 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        TextView footer;
        public ViewHolder(View itemView) {
            super(itemView);
        }
        public ViewHolder(View itemView,int type) {
            super(itemView);
            if(type == 2) {
                tv = (TextView) itemView.findViewById(R.id.tv);
            } else if(type == 3 ) {
                footer = (TextView) itemView.findViewById(R.id.footer);
                footer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("wang","footer click");
                    }
                });
            }
        }
    }
}
