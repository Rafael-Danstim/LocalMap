package com.example.localmap.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.activities.PesquisaActivity;
import com.example.localmap.adapters.CategoriasAdapter;
import com.example.localmap.adapters.EstabelecimentoAdapter;
import com.example.localmap.adapters.EstabelecimentoRecenteAdapter;
import com.example.localmap.databinding.FragmentHomeBinding;
import com.example.localmap.itens_listas.ItemCategoria;
import com.example.localmap.itens_listas.ItemEstabelecimento;
import com.example.localmap.itens_listas.ItemEstabelecimentoRecente;
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

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentHomeBinding binding;

    //Abaixo, RecyclerView das categorias.
    private RecyclerView categoriasRecyclerView;
    private List<Categoria> listaCategoria = new ArrayList<>();
    //Abaixo, RecyclerView dos estabelecimentos recentes.
    private RecyclerView estabelecimentosRecentesRecyclerView;
    private List<Estabelecimento> listaEstabelecimentoRecente = new ArrayList<>();
    //Abaixo, RecyclerView da lista de estabelecimentos no inicio.
    private RecyclerView estabelecimentosRecyclerView;
    private List<Estabelecimento> listaEstabelecimento = new ArrayList<>();

    private EstabelecimentoApi estabelecimentoApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nomeOrdenarPor = root.findViewById(R.id.nomeOrdenarPor);
        TextView abrirPesquisa = root.findViewById(R.id.abrirPesquisa);

        //--> Abaixo, criando um spinner para "Ordenar por".
        Spinner spinnerOrdenarPor = root.findViewById(R.id.ordenarPorSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.ordenar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrdenarPor.setAdapter(adapter);
        spinnerOrdenarPor.setOnItemSelectedListener(this);
        //Abaixo, tornando "Ordenar por" clicavel também.
        nomeOrdenarPor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerOrdenarPor.performClick();
            }
        });

        //--> Abaixo, abrir perquisa.
        abrirPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirPesquisa = new Intent(getContext(), PesquisaActivity.class);
                startActivity(abrirPesquisa);
            }
        });

        //--> Abaixo, configurar a RecyclerView de categorias.
        categoriasRecyclerView = root.findViewById(R.id.categoriasRecyclerView);
        //Chamando o método criarCategorias: "this.criarCategorias();"
        //Abaixo, listando uma quantidade de categorias.
        listaCategoria = ItemCategoria.criarCategorias(50);
        //Abaixo, configurar o adapter de categorias.
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategoria);
        LinearLayoutManager layoutManagerCategorias = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriasRecyclerView.setLayoutManager(layoutManagerCategorias);
        //Abaixo, opcional.
        categoriasRecyclerView.setHasFixedSize(true);
        //Abaixo, adapter.
        categoriasRecyclerView.setAdapter(categoriasAdapter);

        //--> Abaixo, configurar a RecyclerView de estabelecimentos recentes.
        estabelecimentosRecentesRecyclerView = root.findViewById(R.id.estabelecimentosRecentesRecyclerView);
        //Abaixo, listando uma quantidade de estabelecimentos visualizados recentemente.
        listaEstabelecimentoRecente = ItemEstabelecimentoRecente.criarEstabelecimentosRecentes(50);
        EstabelecimentoRecenteAdapter estabelecimentoRecenteAdapter = new EstabelecimentoRecenteAdapter(listaEstabelecimentoRecente);
        LinearLayoutManager layoutManagerEstabelecimentosRecentes = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        estabelecimentosRecentesRecyclerView.setLayoutManager(layoutManagerEstabelecimentosRecentes);
        estabelecimentosRecentesRecyclerView.setHasFixedSize(true);
        estabelecimentosRecentesRecyclerView.setAdapter(estabelecimentoRecenteAdapter);

        //--> Abaixo, configurar a RecyclerView da lista de estabelecimentos no inicio.
        estabelecimentosRecyclerView = root.findViewById(R.id.estabelecimentosRecyclerView);
        //Abaixo, listando uma quantidade de estabelecimentos.
        //listaEstabelecimento = ItemEstabelecimento.criarEstabelecimentos();
        EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimento);
        LinearLayoutManager layoutManagerEstabelecimentosInicio = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        estabelecimentosRecyclerView.setLayoutManager(layoutManagerEstabelecimentosInicio);
        estabelecimentosRecyclerView.setHasFixedSize(true);
        estabelecimentosRecyclerView.setAdapter(estabelecimentoAdapter);

        // Crie a instância do RetrofitService
        RetrofitService retrofitService = new RetrofitService();
        // Obtenha o objeto Retrofit
        Retrofit retrofit = retrofitService.getRetrofit();
        // Crie uma instância da interface da API do Retrofit
        estabelecimentoApi = retrofit.create(EstabelecimentoApi.class);

        // Chame o método para obter a lista de estabelecimentos do servidor
        getAllEstabelecimentos();

        return root;
    }

    // Método para obter a lista de estabelecimentos do servidor usando o Retrofit
    private void getAllEstabelecimentos() {
        Call<List<Estabelecimento>> call = estabelecimentoApi.getAllEstabelecimentos();
        call.enqueue(new Callback<List<Estabelecimento>>() {
            @Override
            public void onResponse(Call<List<Estabelecimento>> call, Response<List<Estabelecimento>> response) {
                if (response.isSuccessful()) {
                    List<Estabelecimento> listaEstabelecimento = response.body();
                    // Atualize a lista de estabelecimentos do adapter
                    EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimento);
                    estabelecimentoAdapter.setListaEstabelecimentos(listaEstabelecimento);

                    // Notifique a RecyclerView sobre as alterações nos dados
                    estabelecimentoAdapter.notifyDataSetChanged();

                    // Configure o adapter atualizado na RecyclerView
                    estabelecimentosRecyclerView.setAdapter(estabelecimentoAdapter);
                } else {
                    Toast.makeText(getContext(), "Falha ao obter os estabelecimentos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Estabelecimento>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // --> Abaixo, os dois metodos do spinner "Ordenar por".
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}