package com.example.localmap.itens_listas;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class ItemEstabelecimentoInicio {

    public static List<Estabelecimento> criarEstabelecimentos() {
        List<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
        int quantidade = 50;
        for (int i = 1; i <= quantidade; i++) {
            Categoria categoria = new Categoria("PetShop" + i);
            Estabelecimento estabelecimento = new Estabelecimento(R.drawable.i2, "Padaria" + i, 1.9 + (i/2), categoria);
            listaEstabelecimentos.add(estabelecimento);
        }
        return listaEstabelecimentos;
    }
}