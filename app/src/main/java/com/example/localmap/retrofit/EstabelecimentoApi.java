package com.example.localmap.retrofit;

import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EstabelecimentoApi {

    @GET("/estabelecimento/get-all")
    Call<List<Estabelecimento>> getAllEstabelecimentos();

    @POST("/estabelecimento/save")
    Call<Estabelecimento> save(@Body Estabelecimento estabelecimento);
}