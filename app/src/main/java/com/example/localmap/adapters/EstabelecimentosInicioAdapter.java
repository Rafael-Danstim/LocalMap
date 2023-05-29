package com.example.localmap.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EstabelecimentosInicioAdapter extends RecyclerView.Adapter<EstabelecimentosInicioAdapter.MyViewHolder> {

    private List<Estabelecimento> listaEstabelecimentoInicio;
    private List<Estabelecimento> listaEstabelecimentoFiltrada;

    public EstabelecimentosInicioAdapter(List<Estabelecimento> lista) {
        this.listaEstabelecimentoInicio = lista;
        this.listaEstabelecimentoFiltrada = new ArrayList<>(lista);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemEstabelecimento = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_estabelecimentos_inicio, parent, false);
        return new EstabelecimentosInicioAdapter.MyViewHolder(itemEstabelecimento);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Estabelecimento estabelecimentoInicio = listaEstabelecimentoInicio.get(position);

        holder.imagemEstabelecimentoInicio.setImageResource(estabelecimentoInicio.getImagem());
        holder.nomeEstabelecimentoInicio.setText(estabelecimentoInicio.getNome());
        holder.avaliacaoEstabelecimentoInicio.setText(String.valueOf(estabelecimentoInicio.getAvaliacao()));
        holder.categoriaEstabelecimentoInicio.setText(estabelecimentoInicio.getCategoria().getNome());
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
