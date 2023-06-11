package com.example.localmap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EstabelecimentoRecenteAdapter extends RecyclerView.Adapter<EstabelecimentoRecenteAdapter.MyViewHolder> {

    private List<Estabelecimento> listaEstabelecimentoRecente;

    public EstabelecimentoRecenteAdapter(List<Estabelecimento> lista) {
        this.listaEstabelecimentoRecente = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemEstabelecimento = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_estabelecimentos_recentes, parent, false);

        return new EstabelecimentoRecenteAdapter.MyViewHolder(itemEstabelecimento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Estabelecimento estabelecimentoRecente = listaEstabelecimentoRecente.get(position);

        holder.imagemDoEstabelecimento.setImageResource(estabelecimentoRecente.getImagem());
        holder.nomeDoEstabelecimento.setText(estabelecimentoRecente.getNome());
    }

    @Override
    public int getItemCount() {
        return listaEstabelecimentoRecente.size();
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