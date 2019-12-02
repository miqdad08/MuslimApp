package com.example.muslimapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AsmaulAdapter extends RecyclerView.Adapter<AsmaulAdapter.NamaHolder> {

    List<ModelNama> list;

    public AsmaulAdapter(List<ModelNama> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NamaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_list, viewGroup, false);
        return new AsmaulAdapter.NamaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NamaHolder namaHolder, int i) {
        namaHolder.bindView(list.get(i));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NamaHolder extends RecyclerView.ViewHolder {
        private TextView Judul;
        private String idJudul;

        public NamaHolder(@NonNull View itemView) {
            super(itemView);
            Judul = itemView.findViewById(R.id.textJudul);
        }

        public void bindView(ModelNama data) {
            Judul.setText(data.getJudul());
            idJudul = data.getId();
            final String idNama = data.getId();
        }

    }
}
