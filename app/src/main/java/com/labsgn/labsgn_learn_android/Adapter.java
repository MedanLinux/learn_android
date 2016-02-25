package com.labsgn.labsgn_learn_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by rhony on 24/02/16.
 *
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    List<Information> data = Collections.emptyList();

    public Adapter (Context context, List<Information> data){
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder  holder, int position) {
        Information currentInformation = data.get(position);
        holder.title.setText(currentInformation.title);
        holder.icon.setImageResource(currentInformation.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recylcerTitle);
            icon  = (ImageView) itemView.findViewById(R.id.recyclerIcon);

            /*Todo 12. Opsional, Tidak perlu listerner lagi karena sudah diterapkan di MainFragment custom click listener
              implements View.OnClickListener juga boleh di hapus

            //title.setOnClickListener(this);
            //icon.setOnClickListener(this);
            */
        }

        @Override
        public void onClick(View v) {
        }
    }

}
