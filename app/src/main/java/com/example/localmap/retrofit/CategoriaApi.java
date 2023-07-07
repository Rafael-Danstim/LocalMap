package com.example.localmap.retrofit;

import com.example.localmap.recycler_view_classes.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoriaApi {

    @GET("/categoria/get-all")
    Call<List<Categoria>> getAllCategorias();

    @POST("/ategoria/save")
    Call<Categoria> save(@Body Categoria categoria);
}