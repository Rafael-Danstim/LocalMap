package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.localmap.R;
import com.example.localmap.adapters.EstabelecimentosInicioAdapter;
import com.example.localmap.itens_listas.ItemEstabelecimentoInicio;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class PesquisaActivity extends AppCompatActivity {

    //Abaixo, RecyclerView da lista de estabelecimentos.
    private RecyclerView estabelecimentosRecyclerView;
    private List<Estabelecimento> listaEstabelecimento = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        //--> Abaixo, adicionando um ouvinte de texto à SearchView.
        SearchView searchView = findViewById(R.id.searchView);
        searchView.requestFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                pesquisarEstabelecimentos(newText);
                return true;
            }
        });

        // RecyclerView com a lista de estabelecimentos.
        estabelecimentosRecyclerView = findViewById(R.id.estabelecimentosRecyclerView);
        listaEstabelecimento = ItemEstabelecimentoInicio.criarEstabelecimentos(50);
        EstabelecimentosInicioAdapter adapter = new EstabelecimentosInicioAdapter(listaEstabelecimento);
        LinearLayoutManager layoutManagerEstabelecimentosInicio = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        estabelecimentosRecyclerView.setLayoutManager(layoutManagerEstabelecimentosInicio);
        estabelecimentosRecyclerView.setHasFixedSize(true);
        estabelecimentosRecyclerView.setAdapter(adapter);
    }

    //Abaixo, método para realizar uma pesquisa na lista de estabelecimentos com base no nome.
    private void pesquisarEstabelecimentos(String query) {
        List<Estabelecimento> resultadosPesquisa = new ArrayList<>();

        for (Estabelecimento estabelecimento : listaEstabelecimento) {
            if (estabelecimento.getNome().toLowerCase().contains(query.toLowerCase())) {
                resultadosPesquisa.add(estabelecimento);
            }
        }

        EstabelecimentosInicioAdapter adapter = (EstabelecimentosInicioAdapter) estabelecimentosRecyclerView.getAdapter();
        adapter.setListaEstabelecimentos(resultadosPesquisa);
        adapter.setListaEstabelecimentosInicio(resultadosPesquisa);
        adapter.notifyDataSetChanged();
    }
}