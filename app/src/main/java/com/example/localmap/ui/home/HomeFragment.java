package com.example.localmap.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.activities.PesquisaActivity;
import com.example.localmap.adapters.CategoriasAdapter;
import com.example.localmap.adapters.EstabelecimentoAdapter;
import com.example.localmap.databinding.FragmentHomeBinding;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.retrofit.CategoriaApi;
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
    //Abaixo, RecyclerView da lista de estabelecimentos no inicio.
    private RecyclerView estabelecimentosRecyclerView;
    private List<Estabelecimento> listaEstabelecimento = new ArrayList<>();

    private EstabelecimentoApi estabelecimentoApi;
    private CategoriaApi categoriaApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

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

        // Cria a instância do RetrofitService
        RetrofitService retrofitService = new RetrofitService();
        // Obtem o objeto Retrofit
        Retrofit retrofit = retrofitService.getRetrofit();
        // Cria uma instância da interface da API do Retrofit
        estabelecimentoApi = retrofit.create(EstabelecimentoApi.class);
        // Cria uma instância da interface da API do Retrofit
        categoriaApi = retrofit.create(CategoriaApi.class);

        //--> Abaixo, configurar a RecyclerView de categorias.
        categoriasRecyclerView = root.findViewById(R.id.categoriasRecyclerView);
        //Abaixo, configurar o adapter de categorias.
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategoria);
        LinearLayoutManager layoutManagerCategorias = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriasRecyclerView.setLayoutManager(layoutManagerCategorias);
        //Abaixo, opcional.
        categoriasRecyclerView.setHasFixedSize(true);
        //Abaixo, adapter.
        categoriasRecyclerView.setAdapter(categoriasAdapter);
        // Chama o método para obter a lista de categorias do servidor
        getAllCategorias();

        //--> Abaixo, configurar a RecyclerView da lista de estabelecimentos no inicio.
        estabelecimentosRecyclerView = root.findViewById(R.id.estabelecimentosRecyclerView);
        EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimento);
        LinearLayoutManager layoutManagerEstabelecimentosInicio = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        estabelecimentosRecyclerView.setLayoutManager(layoutManagerEstabelecimentosInicio);
        estabelecimentosRecyclerView.setHasFixedSize(true);
        estabelecimentosRecyclerView.setAdapter(estabelecimentoAdapter);
        // Chama o método para obter a lista de estabelecimentos do servidor
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
                    // Atualiza a lista de estabelecimentos do adapter
                    EstabelecimentoAdapter estabelecimentoAdapter = new EstabelecimentoAdapter(listaEstabelecimento);
                    estabelecimentoAdapter.setListaEstabelecimentos(listaEstabelecimento);

                    // Notifica a RecyclerView sobre as alterações nos dados
                    estabelecimentoAdapter.notifyDataSetChanged();

                    // Configura o adapter atualizado na RecyclerView
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

                    // Notifica a RecyclerView sobre as alterações nos dados
                    categoriasAdapter.notifyDataSetChanged();

                    // Configura o adapter atualizado na RecyclerView
                    categoriasRecyclerView.setAdapter(categoriasAdapter);
                } else {
                    Toast.makeText(getContext(), "Falha ao obter as categorias", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
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