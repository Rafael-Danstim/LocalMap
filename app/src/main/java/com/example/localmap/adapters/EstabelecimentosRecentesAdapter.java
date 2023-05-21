package com.example.localmap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Estabelecimentos;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EstabelecimentosRecentesAdapter extends RecyclerView.Adapter<EstabelecimentosRecentesAdapter.MyViewHolder> {

    private List<Estabelecimentos> listaEstabelecimentosRecentes;

    public EstabelecimentosRecentesAdapter(List<Estabelecimentos> lista) {
        this.listaEstabelecimentosRecentes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemEstabelecimento = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_estabelecimentos_recentes, parent, false);

        return new EstabelecimentosRecentesAdapter.MyViewHolder(itemEstabelecimento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Estabelecimentos estabelecimentosRecentes = listaEstabelecimentosRecentes.get(position);

        holder.imagemDoEstabelecimento.setImageResource(estabelecimentosRecentes.getImagemDoEstabelecimento());
        holder.nomeDoEstabelecimento.setText(estabelecimentosRecentes.getNomeDoEstabelecimento());
    }

    @Override
    public int getItemCount() {
        return listaEstabelecimentosRecentes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imagemDoEstabelecimento;
        TextView nomeDoEstabelecimento;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemDoEstabelecimento = itemView.findViewById(R.id.imagemDoEstabelecimento);
            nomeDoEstabelecimento = itemView.findViewById(R.id.nomeDoEstabelecimento);

        }
    }
}