package com.rudy.expandable_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.ViewHolder> {

    private List<String> mList;

    public NestedAdapter(List<String> mList) {
        this.mList = mList;
    }

    //Step1
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));
    }

    //Step2
    @Override
    public int getItemCount() {
        return mList.size();
    }


    //Step3
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.nestedItemTv);
        }
    }
}
