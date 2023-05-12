package com.example.localmap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categorias;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> {

    private List<Categorias> listaCategorias;

    public CategoriasAdapter(List<Categorias> lista) {
        this.listaCategorias = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemCategoria = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_categorias, parent, false);

        return new MyViewHolder(itemCategoria);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categorias categorias = listaCategorias.get(position);

        holder.imagemDaCategoria.setBackgroundResource(categorias.getImagemDaCategoria());
        holder.nomeDaCategoria.setText(categorias.getNomeDaCategoria());
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagemDaCategoria;
        TextView nomeDaCategoria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemDaCategoria = itemView.findViewById(R.id.imagemDaCategoria);
            nomeDaCategoria = itemView.findViewById(R.id.nomeDaCategoria);

        }
    }
}