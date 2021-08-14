package com.example.a10118337_uas_akb.view.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a10118337_uas_akb.R;
import com.example.a10118337_uas_akb.model.DataDestinasi;


import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter{
    Context mContext;
    private List<DataDestinasi> list;


    public RecycleAdapter(List<DataDestinasi> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destinasi, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataDestinasi destinasi = list.get(position);
        ((MyViewHolder) holder).bindView(destinasi);
        Glide.with(mContext).load(destinasi.getImgUrl()).into(((MyViewHolder) holder).ImgUrl);
    }


    public RecycleAdapter(Context context, List<DataDestinasi> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nama;
        TextView keterangan;
        ImageView ImgUrl;
        TextView alamat;

        CardView card_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ImgUrl = (ImageView) itemView.findViewById(R.id.ImgUrl);
            nama = itemView.findViewById(R.id.nama);
            keterangan = itemView.findViewById(R.id.keterangan);
            alamat = itemView.findViewById(R.id.alamat);

            itemView.setOnClickListener(this);

        }

        public void bindView(DataDestinasi dataDestinasi) {
            nama.setText(dataDestinasi.getNama());
            keterangan.setText(dataDestinasi.getKeterangan());
            alamat.setText(dataDestinasi.getAlamat());
        }

        @Override
        public void onClick(View v) {
            System.out.println(nama.getText());
        }
    }
}
