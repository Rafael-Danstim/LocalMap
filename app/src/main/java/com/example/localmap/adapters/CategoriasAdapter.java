package com.example.localmap.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.activities.CategoriaActivity;
import com.example.localmap.recycler_view_classes.Categoria;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> {

    private List<Categoria> listaCategorias;

    public CategoriasAdapter(List<Categoria> lista) {
        this.listaCategorias = lista;
    }

    public void setListaCategorias(List<Categoria> lista) {
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
        Categoria categoria = listaCategorias.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenha a categoria correspondente ao item clicado
                Categoria categoria = listaCategorias.get(position);

                // Abra a tela de categoria com os estabelecimentos correspondentes
                Intent intent = new Intent(view.getContext(), CategoriaActivity.class);
                intent.putExtra("categoria", categoria);
                view.getContext().startActivity(intent);
            }
        });


        //holder.imagemDaCategoria.setBackgroundResource(categoria.getImagem());
        Picasso.get().load(categoria.getImagem()).into(holder.imagemDaCategoria);
        holder.nomeDaCategoria.setText(categoria.getNome());
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