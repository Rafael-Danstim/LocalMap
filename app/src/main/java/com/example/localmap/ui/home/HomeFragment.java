package com.example.localmap.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.adapters.CategoriasAdapter;
import com.example.localmap.adapters.EstabelecimentosInicioAdapter;
import com.example.localmap.adapters.EstabelecimentosRecentesAdapter;
import com.example.localmap.databinding.FragmentHomeBinding;
import com.example.localmap.itens_listas.ItemCategoria;
import com.example.localmap.itens_listas.ItemEstabelecimentoInicio;
import com.example.localmap.itens_listas.ItemEstabelecimentoRecente;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentHomeBinding binding;

    //Abaixo, RecyclerView das categorias.
    private RecyclerView categoriasRecyclerView;
    private List<Categoria> listaCategoria = new ArrayList<>();
    //Abaixo, RecyclerView dos estabelecimentos recentes.
    private RecyclerView estabelecimentosRecentesRecyclerView;
    private List<Estabelecimento> listaEstabelecimentoRecente = new ArrayList<>();
    //Abaixo, RecyclerView da lista de estabelecimentos no inicio.
    private RecyclerView estabelecimentosInicioRecyclerView;
    private List<Estabelecimento> listaEstabelecimentoInicio = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView nomeOrdenarPor = root.findViewById(R.id.nomeOrdenarPor);

        // Abaixo, criando um spinner para "Ordenar por".
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
        EstabelecimentosRecentesAdapter estabelecimentosRecentesAdapter = new EstabelecimentosRecentesAdapter(listaEstabelecimentoRecente);
        LinearLayoutManager layoutManagerEstabelecimentosRecentes = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        estabelecimentosRecentesRecyclerView.setLayoutManager(layoutManagerEstabelecimentosRecentes);
        estabelecimentosRecentesRecyclerView.setHasFixedSize(true);
        estabelecimentosRecentesRecyclerView.setAdapter(estabelecimentosRecentesAdapter);

        //--> Abaixo, configurar a RecyclerView da lista de estabelecimentos no inicio.
        estabelecimentosInicioRecyclerView = root.findViewById(R.id.estabelecimentosInicioRecyclerView);
        //Abaixo, listando uma quantidade de estabelecimentos.
        listaEstabelecimentoInicio = ItemEstabelecimentoInicio.criarEstabelecimentos(50);
        EstabelecimentosInicioAdapter estabelecimentosInicioAdapter = new EstabelecimentosInicioAdapter(listaEstabelecimentoInicio);
        LinearLayoutManager layoutManagerEstabelecimentosInicio = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        estabelecimentosInicioRecyclerView.setLayoutManager(layoutManagerEstabelecimentosInicio);
        estabelecimentosInicioRecyclerView.setHasFixedSize(true);
        estabelecimentosInicioRecyclerView.setAdapter(estabelecimentosInicioAdapter);

        return root;
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