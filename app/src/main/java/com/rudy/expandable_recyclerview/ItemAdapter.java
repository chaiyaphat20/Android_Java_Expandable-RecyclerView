package com.rudy.expandable_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<DataModel> mList;
    private List<String> list = new ArrayList<>();

    public ItemAdapter(List<DataModel> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataModel modal = mList.get(position);
        holder.mTextView.setText(modal.getItemText());

        boolean isExpandable = modal.isExpandable();
        //เงื่อนไชโชว์ layout more
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        //เงื่อไนไข ลูกศร
        if (isExpandable) {
            holder.mArrowImage.setImageResource(R.drawable.arrow_up);
        } else {
            holder.mArrowImage.setImageResource(R.drawable.arrow_down);
        }

        //nested recycleView
        NestedAdapter adapter = new NestedAdapter(list);
        holder.nestedRecycleView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        holder.nestedRecycleView.setHasFixedSize(true);
        holder.nestedRecycleView.setAdapter(adapter);

        //Toggle show/hide
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modal.setExpandable(!modal.isExpandable());
                list = modal.getNestedList();
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private RelativeLayout expandableLayout;
        private TextView mTextView;
        private ImageView mArrowImage;

        private RecyclerView nestedRecycleView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            mTextView = itemView.findViewById(R.id.itemTv);
            mArrowImage = itemView.findViewById(R.id.arro_imageview);
            nestedRecycleView = itemView.findViewById(R.id.child_rv);
        }
    }

}
