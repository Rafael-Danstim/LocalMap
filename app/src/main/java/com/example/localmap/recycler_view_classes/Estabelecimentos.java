package com.example.localmap.recycler_view_classes;

import android.net.Uri;

public class Estabelecimentos {
    private String nomeDoEstabelecimento;
    private Categorias nomeDaCategoria;
    private String endereco;
    private String rotaGoogleMaps;
    private int imagemDoEstabelecimento;
    private double avaliacao;
    private String telefoneContato;
    private boolean favoritado;

    //Construtor Geral da classe
    public Estabelecimentos(String nomeDoEstabelecimento, Categorias nomeDaCategoria, String endereco, String rotaGoogleMaps, int imagemDoEstabelecimento, double avaliacao, String telefoneContato, boolean favoritado) {
        this.nomeDoEstabelecimento = nomeDoEstabelecimento;
        this.nomeDaCategoria = nomeDaCategoria;
        this.endereco = endereco;
        this.rotaGoogleMaps = rotaGoogleMaps;
        this.imagemDoEstabelecimento = imagemDoEstabelecimento;
        this.avaliacao = avaliacao;
        this.telefoneContato = telefoneContato;
        this.favoritado = favoritado;
    }

    //Construtor para a RecyclerView dos estabelecimentos recentemente visitados na tela de inicio.
    public Estabelecimentos(int imagemDoEstabelecimento, String nomeDoEstabelecimento) {
        this.imagemDoEstabelecimento = imagemDoEstabelecimento;
        this.nomeDoEstabelecimento = nomeDoEstabelecimento;
    }

    public String getNomeDoEstabelecimento() {
        return nomeDoEstabelecimento;
    }

    public void setNomeDoEstabelecimento(String nomeDoEstabelecimento) {
        this.nomeDoEstabelecimento = nomeDoEstabelecimento;
    }

    public Categorias getNomeDaCategoria() {
        return nomeDaCategoria;
    }

    public void setNomeDaCategoria(Categorias nomeDaCategoria) {
        this.nomeDaCategoria = nomeDaCategoria;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRotaGoogleMaps() {
        return rotaGoogleMaps;
    }

    public void setRotaGoogleMaps(String rotaGoogleMaps) {
        this.rotaGoogleMaps = rotaGoogleMaps;
    }

    public int getImagemDoEstabelecimento() {
        return imagemDoEstabelecimento;
    }

    public void setImagemDoEstabelecimento(int imagemDoEstabelecimento) {
        this.imagemDoEstabelecimento = imagemDoEstabelecimento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public boolean isFavoritado() {
        return favoritado;
    }

    public void setFavoritado(boolean favoritado) {
        this.favoritado = favoritado;
    }
}