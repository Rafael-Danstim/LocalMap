package com.example.localmap.ui.pesquisa;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.localmap.R;
import com.example.localmap.activities.PesquisaActivity;
import com.example.localmap.adapters.CategoriasAdapter;
import com.example.localmap.databinding.FragmentPesquisaBinding;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.retrofit.CategoriaApi;
import com.example.localmap.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PesquisaFragment extends Fragment {

    private FragmentPesquisaBinding binding;
    private RecyclerView categoriasRecyclerView;
    private List<Categoria> listaCategoria = new ArrayList<>();
    private CategoriaApi categoriaApi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPesquisaBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_pesquisa, container, false);

        TextView abrirPesquisa = root.findViewById(R.id.abrirPesquisa);

        //--> Abaixo, abrir perquisa.
        abrirPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirPesquisa = new Intent(getContext(), PesquisaActivity.class);
                startActivity(abrirPesquisa);
            }
        });

        //--> RecyclerView na Pesquisa
        // Inicializa a RecyclerView de categorias
        categoriasRecyclerView = root.findViewById(R.id.categoriasRecyclerView);
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategoria);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2); // Duas colunas
        categoriasRecyclerView.setLayoutManager(layoutManager);
        categoriasRecyclerView.setAdapter(categoriasAdapter);

        // Cria a instância do RetrofitService
        RetrofitService retrofitService = new RetrofitService();
        // Obtem o objeto Retrofit
        Retrofit retrofit = retrofitService.getRetrofit();
        // Cria uma instância da interface da API do Retrofit
        categoriaApi = retrofit.create(CategoriaApi.class);

        // Chama o método para obter a lista de categorias do servidor
        getAllCategorias();

        return root;
    }

    private void getAllCategorias() {
        Call<List<Categoria>> call = categoriaApi.getAllCategorias();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                if (response.isSuccessful()) {
                    List<Categoria> listaCategorias = response.body();
                    // Atualiza a lista de categorias do adapter
                    CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategorias);
                    categoriasAdapter.setListaCategorias(listaCategorias);
                    // Configura o adapter atualizado na RecyclerView
                    categoriasRecyclerView.setAdapter(categoriasAdapter);
                } else {
                    // Lida com o erro de resposta não bem sucedida
                    // ...
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                // Lida com o erro de conexão
                // ...
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}