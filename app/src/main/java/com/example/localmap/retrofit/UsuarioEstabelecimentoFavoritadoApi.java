package com.example.localmap.retrofit;

import com.example.localmap.recycler_view_classes.Estabelecimento;
import com.example.localmap.recycler_view_classes.UsuarioEstabelecimentoFavoritado;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UsuarioEstabelecimentoFavoritadoApi {

    @POST("/favoritar")
    Call<UsuarioEstabelecimentoFavoritado> favoritarEstabelecimento(@Body UsuarioEstabelecimentoFavoritado usuarioEstabelecimentoFavoritado);

    @DELETE("/desfavoritar")
    Call<Void> desfavoritarEstabelecimento(@Query("usuarioId") int usuarioId, @Query("estabelecimentoId") int estabelecimentoId);

    @GET("/verificarFavorito")
    Call<UsuarioEstabelecimentoFavoritado> verificarFavorito(@Query("usuarioId") int usuarioId, @Query("estabelecimentoId") int estabelecimentoId);

    // Novo endpoint para obter a lista de estabelecimentos favoritados para um determinado usuário
    @GET("/estabelecimentosFavoritados")
    Call<List<UsuarioEstabelecimentoFavoritado>> getEstabelecimentosFavoritados(@Query("usuarioId") int usuarioId);
}