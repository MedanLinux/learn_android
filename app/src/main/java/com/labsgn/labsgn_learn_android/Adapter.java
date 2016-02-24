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
 * Todo 7. Membuat adapter untuk recyclerView
 * Todo 9. Extend  class MyViewHolder extends RecyclerView.ViewHolder
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private LayoutInflater inflater;

    //Todo 16. Membuat list untuk menyimpan data dari class Information
    List<Information> data = Collections.emptyList();

    // Todo 11. Membuat constructor layout inflater untuk menyimpan layout
    public Adapter (Context context, List<Information> data){
        inflater = LayoutInflater.from(context);

        //Todo 17. Simpan data
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Todo 12. Menghubungkan root view dari custom row "main_recycler_row" yaitu LinearLayout
        View view = inflater.inflate(R.layout.main_recycler_row, parent, false);

        //Todo 13. Masukkan view ke holder untuk menemukan ImageView & TextView yang dipakai di dalam main_recycler_row
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder  holder, int position) {
        //Todo 18. Mengambil dan menyimpan data
        Information currentInformation = data.get(position);
        holder.title.setText(currentInformation.title);
        holder.icon.setImageResource(currentInformation.iconId);
    }

    @Override
    public int getItemCount() {
        //Todo 19. Membuat akses ke data
        return data.size();
    }

    //Todo 8. Membuat holder baru untuk di class "Adapter"
    class MyViewHolder extends RecyclerView.ViewHolder {
        //Todo 14. Membuat instance baru untuk penyimpanan
        private TextView title;
        private ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            //Todo 15. Simpan variabel
            title = (TextView) itemView.findViewById(R.id.recylcerTitle);
            icon  = (ImageView) itemView.findViewById(R.id.recyclerIcon);
        }
    }

}
