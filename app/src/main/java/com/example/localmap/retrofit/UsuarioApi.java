package com.example.localmap.retrofit;

import com.example.localmap.recycler_view_classes.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioApi {
    @POST("/usuario/save")
    Call<Usuario> save(@Body Usuario usuario);
}