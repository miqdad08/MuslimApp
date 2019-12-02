package com.example.muslimapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DzikirAdapter extends RecyclerView.Adapter<DzikirAdapter.DzikirHolder> {

    private ArrayList<ModelDzikir> listData;

    public DzikirAdapter(ArrayList<ModelDzikir> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public DzikirHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_dzikir, viewGroup, false);
        return new DzikirHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DzikirHolder dzikirHolder, int i) {
        dzikirHolder.bindView(listData.get(i));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
        public class DzikirHolder extends RecyclerView.ViewHolder {
            private TextView tvNo, tvJudul, tvSubJudul;

            public DzikirHolder(@NonNull View itemView) {
                super(itemView);
                tvNo = itemView.findViewById(R.id.tvNo);
                tvJudul = itemView.findViewById(R.id.tvJudul);
                tvSubJudul = itemView.findViewById(R.id.tvSubJudul);
            }

            private void bindView(ModelDzikir data) {
                tvNo.setText(data.getNo());
                tvJudul.setText(data.getJudul());
                tvSubJudul.setText(data.getSubjudul());
                final String idDzikir = data.getId();
            }
        }
    }