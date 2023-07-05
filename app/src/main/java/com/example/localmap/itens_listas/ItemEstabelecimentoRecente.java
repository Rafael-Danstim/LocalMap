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
            Estabelecimento estabelecimento = new Estabelecimento(
                    "https://lh3.googleusercontent.com/fife/APg5EOa3z9V-I3lV8XDWnnUF2fi9JJIwgrNVr56aPc-oYUIOFv4Tk_qPdeiIOaxP979R-eR_T1P5YYKavuewxhmp5Jzu9EWAs-QrczJQICLvAp_Ogqo69Kqt2R-5Zt8ODJ2DI0EzNUUfFgfUpmBF0O2mupBLHOfGn0rlCXLIEdpNDpdTagQMPzdfEAhGILxKpbyiiq1ImL2ib5zaqccD7DAZftBtC8juhO-z_xEZxcbHtAuFP5MeV69d5KFo6amd-AqI0sx2UoI4xPTFsPA-RuUC6RucLLDmiD9yfmPKPFaEbuUd2ZAyDT4MgQFz2UM_cVDaS7caSBNK1v5ZqhZGbfXp6O447CLpPF4dHodcpH9Uq5gJRcUBDnfvIzKF9QUZXfWClDTId8VSN3Ad2PaepGi8A9ueGA3MJSOWZkhg_xoDGT2HA1HMeOjuH1Qzn8zGhH7HiTjl8uHFZ8QjbFodFKWzgXV4tXCHDvmLYIDjIwC07WQw-3yPC2uqiMQXKiLjtI0LVB7drxjlkoAp4tmafldJCcgwDlD4ZqbADZQc4f40-d7jhwhg8KQIXes0swEGKcX8jHepUGB3CKTCwtWHnxW5rQmEynK9xRHWKctHilEK5-t5ZOz_MMGbQSR-3bv3D3QGth0GmQyW0K10K-eGZg7UQIWATkQmpKbvjxjqg9oq8sUYu5IpeMp5vf8dmnxTgA8fjBvO0MSnQNzosrSW1RwFKVQ5Vd6eclKbKmPlhK_UJSmw76J_4mFr7TAv7sOwe4uoJMUAAMZw8epxmkOoAi3EZxCJQKQou7HTg4gtXvb_5_1zdErN-TVxPvL-ip-OspXzHSfVYJC6zGhWFxAE6azbjpEQqVwg1g7ROElS7rFRnd6AQT7Mm_WHX4VGnTB07cT2GhIxc7pz84Nr9LV0UlE6qIQPSJituSk1hyJCONKFXIKtE5ScMNloXvaT4GoLIGyKoq1-guDwXsUmHCiXNuGZZvd3vu5vOEuprIrqPmSIsSmbqqY1aG5_KnT6t21T9C3GKf4e8X5EP9T0KS_BZpapqnpjlFBr-3NDjFpQSmBgnrFnGe6d2rRP7RulMuTcBmuyWp5M_ng5JoxHy6FdbA5j6IZgH9nh3OJocUYWYQ79B7AW0vjIKZy2B0Z5XjHQxhOVXyN1v__YrBpclpoFTBXtzAjaXjtq9bOtc_Yrg6LwLsauZSMY9uNmX9C9FOvN6xXKKXEFfvNTlkaZCUdVfu7Ul69lWa7LrO94VVSjS8PoskatHSKgPDmG5QxDz5Me5XXQ2vonVJ3OzK1pq2k63aEoca6lAd2SVsiRwXOUhtQd7oj0_MHe4hExApatCtGqpk6hw3APccmjzvlKmMFk8S9dof0pbNc5SDZ7W24iMGz749v1jn_unhWbc583LN_90yu1NgM0boucQnt1xOfP4PYF4tJIhRxUkF03FEMJ46RqPTQRWQaVKA-n_t3ARIOSQjZq-4GcSA4W8zDrJfMJ260fH-NfpFXjb6zlA6w_-5gXdwshoX5mIzphFQ7psAKe9Ph36v0jIK_CS8OdEiQMrwLYH4_-TUmOmdjqF7-tAhJanxMt941xLnHVIxZnOWm8hRnuG1-WlVeJs1IHjRo=w957-h663",
                    "Loja de Carpintaria" + i);
            listaEstabelecimentosRecentes.add(estabelecimento);
        }
        return listaEstabelecimentosRecentes;
    }
}
