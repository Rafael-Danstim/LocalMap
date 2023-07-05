package com.example.localmap.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.localmap.R;
import com.example.localmap.adapters.EstabelecimentoAdapter;
import com.example.localmap.itens_listas.ItemEstabelecimento;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.retrofit.EstabelecimentoApi;
import com.example.localmap.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PesquisaActivity extends AppCompatActivity {

    //Abaixo, RecyclerView da lista de estabelecimentos.
    private RecyclerView estabelecimentosRecyclerView;
    private List<Estabelecimento> listaEstabelecimento = new ArrayList<>();
    private EstabelecimentoApi estabelecimentoApi;

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
        //listaEstabelecimento = ItemEstabelecimento.criarEstabelecimentos();
        EstabelecimentoAdapter adapter = new EstabelecimentoAdapter(listaEstabelecimento);
        LinearLayoutManager layoutManagerEstabelecimentosInicio = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        estabelecimentosRecyclerView.setLayoutManager(layoutManagerEstabelecimentosInicio);
        estabelecimentosRecyclerView.setHasFixedSize(true);
        estabelecimentosRecyclerView.setAdapter(adapter);

        // Crie a instância do RetrofitService
        RetrofitService retrofitService = new RetrofitService();
        // Obtenha o objeto Retrofit
        Retrofit retrofit = retrofitService.getRetrofit();
        // Crie uma instância da interface da API do Retrofit
        estabelecimentoApi = retrofit.create(EstabelecimentoApi.class);

        // Chame o método para obter a lista de estabelecimentos do servidor
        getAllEstabelecimentos();

    }

    // Método para obter a lista de estabelecimentos do servidor usando o Retrofit
    private void getAllEstabelecimentos() {
        Call<List<Estabelecimento>> call = estabelecimentoApi.getAllEstabelecimentos();
        call.enqueue(new Callback<List<Estabelecimento>>() {
            @Override
            public void onResponse(Call<List<Estabelecimento>> call, Response<List<Estabelecimento>> response) {
                if (response.isSuccessful()) {
                    listaEstabelecimento = response.body(); // Update the global variable here
                    // Atualize a lista de estabelecimentos do adapter
                    EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimento);
                    estabelecimentoAdapter.setListaEstabelecimentos(listaEstabelecimento);

                    // Notifique a RecyclerView sobre as alterações nos dados
                    estabelecimentoAdapter.notifyDataSetChanged();

                    // Configure o adapter atualizado na RecyclerView
                    estabelecimentosRecyclerView.setAdapter(estabelecimentoAdapter);
                } else {
                    Toast.makeText(PesquisaActivity.this, "Falha ao obter os estabelecimentos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Toast.makeText(PesquisaActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Abaixo, método para realizar uma pesquisa na lista de estabelecimentos com base no nome.
    private void pesquisarEstabelecimentos(String query) {
        List<Estabelecimento> resultadosPesquisa = new ArrayList<>();

        for (Estabelecimento estabelecimento : listaEstabelecimento) {
            if (estabelecimento.getNome().toLowerCase().contains(query.toLowerCase())) {
                resultadosPesquisa.add(estabelecimento);
            }
        }

        EstabelecimentoAdapter adapter = (EstabelecimentoAdapter) estabelecimentosRecyclerView.getAdapter();
        adapter.setListaEstabelecimentos(resultadosPesquisa);
        adapter.setListaEstabelecimentosInicio(resultadosPesquisa);
        adapter.notifyDataSetChanged();
    }
}