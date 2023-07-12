package com.example.localmap.retrofit;

import com.example.localmap.recycler_view_classes.Categoria;
import com.example.localmap.recycler_view_classes.Estabelecimento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoriaApi {

    @GET("/categoria/get-all")
    Call<List<Categoria>> getAllCategorias();

    @POST("/categoria/save")
    Call<Categoria> save(@Body Categoria categoria);

}