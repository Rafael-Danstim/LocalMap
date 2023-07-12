package com.example.localmap.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.localmap.R;
import com.example.localmap.adapters.EstabelecimentoAdapter;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.retrofit.EstabelecimentoApi;
import com.example.localmap.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoriaActivity extends AppCompatActivity {

    private RecyclerView estabelecimentosRecyclerView;
    private List<Estabelecimento> listaEstabelecimento = new ArrayList<>();
    private EstabelecimentoApi estabelecimentoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        // RecyclerView com a lista de estabelecimentos.
        estabelecimentosRecyclerView = findViewById(R.id.estabelecimentosRecyclerView);
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

        // Obtenha a categoria selecionada da Intent
        Intent intent = getIntent();
        if (intent.hasExtra("categoria")) {
            Categoria categoria = (Categoria) intent.getSerializableExtra("categoria");
            // Chame o método para obter a lista de estabelecimentos com base na categoria selecionada
            getAllEstabelecimentos(categoria);

            Toolbar toolbar2 = findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar2);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(categoria.getNome());
        }

    }

    // Método para obter a lista de estabelecimentos do servidor usando o Retrofit
    private void getAllEstabelecimentos(Categoria categoria) {
        Call<List<Estabelecimento>> call = estabelecimentoApi.getEstabelecimentosByCategoria(categoria.getId());
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
                    Toast.makeText(CategoriaActivity.this, "Falha ao obter os estabelecimentos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Toast.makeText(CategoriaActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}