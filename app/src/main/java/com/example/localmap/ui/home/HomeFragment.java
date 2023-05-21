package com.example.localmap.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localmap.R;
import com.example.localmap.adapters.CategoriasAdapter;
import com.example.localmap.adapters.EstabelecimentosRecentesAdapter;
import com.example.localmap.databinding.FragmentHomeBinding;
import com.example.localmap.recycler_view_classes.Categorias;
import com.example.localmap.recycler_view_classes.Estabelecimentos;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    //Abaixo, RecyclerView das categorias.
    private RecyclerView categoriasRecyclerView;
    private List<Categorias> listaCategorias = new ArrayList<>();
    //Abaixo, RecyclerView dos estabelecimentos.
    private RecyclerView estabelecimentosRecentesRecyclerView;
    private List<Estabelecimentos> listaEstabelecimentosRecentes = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //--> Abaixo, configurar a RecyclerView de categorias.
        categoriasRecyclerView = root.findViewById(R.id.categoriasRecyclerView);
        //Abaixo, chamando o método criarCategorias.
        this.criarCategorias();
        //Abaixo, configurar o adapter de categorias.
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(listaCategorias);
        //Padrão: RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManagerCategorias = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriasRecyclerView.setLayoutManager(layoutManagerCategorias);
        //Abaixo, opcional.
        categoriasRecyclerView.setHasFixedSize(true);
        //Abaixo, adapter.
        categoriasRecyclerView.setAdapter(categoriasAdapter);

        //--> Abaixo, configurar a RecyclerView de estabelecimentos.
        estabelecimentosRecentesRecyclerView = root.findViewById(R.id.estabelecimentosRecentesRecyclerView);
        this.criarEstabelecimentosRecentes();
        EstabelecimentosRecentesAdapter estabelecimentosRecentesAdapter = new EstabelecimentosRecentesAdapter(listaEstabelecimentosRecentes);
        LinearLayoutManager layoutManagerEstabelecimentosRecentes = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        estabelecimentosRecentesRecyclerView.setLayoutManager(layoutManagerEstabelecimentosRecentes);
        estabelecimentosRecentesRecyclerView.setHasFixedSize(true);
        estabelecimentosRecentesRecyclerView.setAdapter(estabelecimentosRecentesAdapter);

        return root;
    }

    //--> Abaixo, Criando método criarCategorias.
    public void criarCategorias() {
        /* A primeira linha, abaixo, representa a solução da inicialização dos itens da lista
           somados aos que já aparecial, toda vez que se alterava de fragmento, no menu!!!!!! */
        listaCategorias.clear();
        Categorias categoria1 = new Categorias(R.drawable.c1, "Nova Vida1");
        listaCategorias.add(categoria1);
        Categorias categoria2 = new Categorias(R.drawable.c2, "Nova Vida2");
        listaCategorias.add(categoria2);
        Categorias categoria3 = new Categorias(R.drawable.c3, "Nova Vida3");
        listaCategorias.add(categoria3);
        Categorias categoria4 = new Categorias(R.drawable.c4, "Nova Vida4");
        listaCategorias.add(categoria4);
        Categorias categoria5 = new Categorias(R.drawable.c5, "Nova Vida5");
        listaCategorias.add(categoria5);
        Categorias categoria6 = new Categorias(R.drawable.c1, "Nova Vida6");
        listaCategorias.add(categoria6);
        Categorias categoria7 = new Categorias(R.drawable.c2, "Nova Vida7");
        listaCategorias.add(categoria7);
    }

    //--> Abaixo, Criando método criarEstabelecimentos.
    public void criarEstabelecimentosRecentes() {
        listaEstabelecimentosRecentes.clear();
        Estabelecimentos estabelecimento1 = new Estabelecimentos(R.drawable.i1, "Padaria1");
        listaEstabelecimentosRecentes.add(estabelecimento1);
        Estabelecimentos estabelecimento2 = new Estabelecimentos(R.drawable.i2, "Padaria2");
        listaEstabelecimentosRecentes.add(estabelecimento2);
        Estabelecimentos estabelecimento3 = new Estabelecimentos(R.drawable.i3, "Padaria3");
        listaEstabelecimentosRecentes.add(estabelecimento3);
        Estabelecimentos estabelecimento4 = new Estabelecimentos(R.drawable.i4, "Padaria4");
        listaEstabelecimentosRecentes.add(estabelecimento4);
        Estabelecimentos estabelecimento5 = new Estabelecimentos(R.drawable.i5, "Padaria5");
        listaEstabelecimentosRecentes.add(estabelecimento5);
        Estabelecimentos estabelecimento6 = new Estabelecimentos(R.drawable.i6, "Padaria6");
        listaEstabelecimentosRecentes.add(estabelecimento6);
        Estabelecimentos estabelecimento7 = new Estabelecimentos(R.drawable.i7, "Padaria7");
        listaEstabelecimentosRecentes.add(estabelecimento7);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}