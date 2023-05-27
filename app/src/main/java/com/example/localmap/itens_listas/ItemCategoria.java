package com.example.localmap.itens_listas;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ItemCategoria {
    public static List<Categoria> criarCategorias(int quantidade) {
        List<Categoria> listaCategorias = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Categoria categoria = new Categoria(R.drawable.i1, "Categoria de Carpintaria " + i );
            listaCategorias.add(categoria);
        }
        return listaCategorias;
    }
}
