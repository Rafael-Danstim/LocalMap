package com.example.localmap.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.activities.EstabelecimentoActivity;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EstabelecimentoAdapter extends RecyclerView.Adapter<EstabelecimentoAdapter.MyViewHolder> {

    private List<Estabelecimento> listaEstabelecimentoInicio;
    private List<Estabelecimento> listaEstabelecimentoFiltrada;

    public EstabelecimentoAdapter(List<Estabelecimento> lista) {
        this.listaEstabelecimentoInicio = lista;
        this.listaEstabelecimentoFiltrada = new ArrayList<>(lista);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemEstabelecimento = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_estabelecimentos_inicio, parent, false);
        return new EstabelecimentoAdapter.MyViewHolder(itemEstabelecimento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Estabelecimento estabelecimentoInicio = listaEstabelecimentoInicio.get(position);
        Estabelecimento estabelecimento = listaEstabelecimentoFiltrada.get(position);

        // Abaixo, torna cada item da lista clicável.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtem o estabelecimento correspondente ao item clicado
                Estabelecimento estabelecimento = listaEstabelecimentoInicio.get(position);

                // Abre a tela de estabelecimento com os dados do estabelecimento clicado
                Intent intent = new Intent(view.getContext(), EstabelecimentoActivity.class);
                intent.putExtra("estabelecimento", estabelecimento);
                view.getContext().startActivity(intent);
            }
        });

        //holder.imagemEstabelecimentoInicio.setImageResource(estabelecimento.getImagem());
        Picasso.get().load(estabelecimento.getImagem()).into(holder.imagemEstabelecimentoInicio);
        holder.nomeEstabelecimentoInicio.setText(estabelecimento.getNome());
        holder.avaliacaoEstabelecimentoInicio.setText(String.format("%.1f", estabelecimento.getAvaliacao()));
        holder.categoriaEstabelecimentoInicio.setText(estabelecimento.getCategoria().getNome());
    }

    @Override
    public int getItemCount() {
        return listaEstabelecimentoFiltrada.size();
    }

    public void setListaEstabelecimentos(List<Estabelecimento> listaEstabelecimentos) {
        listaEstabelecimentoFiltrada = new ArrayList<>(listaEstabelecimentos);
        notifyDataSetChanged();
    }

    public void setListaEstabelecimentosInicio(List<Estabelecimento> listaEstabelecimentos) {
        listaEstabelecimentoInicio = new ArrayList<>(listaEstabelecimentos);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imagemEstabelecimentoInicio;
        TextView nomeEstabelecimentoInicio;
        TextView avaliacaoEstabelecimentoInicio;
        TextView categoriaEstabelecimentoInicio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imagemEstabelecimentoInicio = itemView.findViewById(R.id.imagemEstabelecimentoInicio);
            nomeEstabelecimentoInicio = itemView.findViewById(R.id.nomeEstabelecimentoInicio);
            avaliacaoEstabelecimentoInicio = itemView.findViewById(R.id.avaliacaoEstabelecimentoInicio);
            categoriaEstabelecimentoInicio = itemView.findViewById(R.id.categoriaEstabelecimentoInicio);
        }
    }
}