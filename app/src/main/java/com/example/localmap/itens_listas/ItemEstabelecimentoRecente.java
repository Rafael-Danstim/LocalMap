package com.example.localmap.itens_listas;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class ItemEstabelecimentoRecente {
    public static List<Estabelecimento> criarEstabelecimentosRecentes(int quantidade) {
        List<Estabelecimento> listaEstabelecimentosRecentes = new ArrayList<>();
        for (int i = 1; i <= quantidade; i++) {
            Estabelecimento estabelecimento = new Estabelecimento(R.drawable.i3, "Loja de Carpintaria" + i);
            listaEstabelecimentosRecentes.add(estabelecimento);
        }
        return listaEstabelecimentosRecentes;
    }
}
