package com.example.localmap.itens_listas;

import com.example.localmap.R;
import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class ItemEstabelecimento {

    public static List<Estabelecimento> criarEstabelecimentos() {
        List<Estabelecimento> listaEstabelecimentos = new ArrayList<Estabelecimento>();
        int quantidade = 50;
        for (int i = 1; i <= quantidade; i++) {
            Categoria categoria1 = new Categoria("Supermercado" + i);
            if (i % 2 == 0) {
                Estabelecimento estabelecimento = new Estabelecimento("Nova Vida" + i, categoria1,
                        "Avenida Ministro - Av. José Américo, 480 - Santa Luzia, PB, " + i + "000-000",
                        "rota não definida", R.drawable.i1, 5.0 - (0.2 * (i/2)),
                        "(" + i + ")334612728", false);
                listaEstabelecimentos.add(estabelecimento);
            } else {
                Estabelecimento estabelecimento = new Estabelecimento("Nova Vida" + i, categoria1,
                        "Avenida Ministro - Av. José Américo, 480 - Santa Luzia, PB, " + i + "000-000",
                        "rota não definida", R.drawable.i2, 5.0 - (0.2 * (i/2)),
                        "(" + i + ")334612728", true);
                listaEstabelecimentos.add(estabelecimento);
            }
        }
        return listaEstabelecimentos;
    }
}