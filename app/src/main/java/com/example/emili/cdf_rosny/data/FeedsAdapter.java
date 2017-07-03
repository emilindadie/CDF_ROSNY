package com.example.emili.cdf_rosny.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emili.cdf_rosny.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by emili on 03/06/2017.
 */

public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    List<FeedsModel> data = Collections.emptyList();

    public FeedsAdapter(Context context, List<FeedsModel> data){

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.my_items, parent, false);
        MyHolder holder = new MyHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        FeedsModel current = data.get(position);
        myHolder.textFirstName.setText(current.id);
        myHolder.textType.setText(current.type);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView textFirstName;
        TextView textType;
        public MyHolder(View itemView) {
            super(itemView);
            textFirstName = (TextView) itemView.findViewById(R.id.textViewName);
            textType = (TextView) itemView.findViewById(R.id.textViewType);
        }
    }
}
