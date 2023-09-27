package com.example.localmap.retrofit;

import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService() {
        initializeRetrofit();
    }

    // Minha casa: http://10.0.0.17:9000
    // IFPB: http://10.0.0.89:9000
    // Alunos3: http://192.168.10.21:9000
    private void initializeRetrofit() {
        Gson gson = new Gson();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.0.17:9000")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public UsuarioEstabelecimentoFavoritadoApi getUsuarioEstabelecimentoFavoritadoApi() {
        return retrofit.create(UsuarioEstabelecimentoFavoritadoApi.class);
    }
}
