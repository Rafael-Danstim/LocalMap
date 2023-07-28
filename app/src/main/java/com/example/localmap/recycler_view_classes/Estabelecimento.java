package com.example.localmap.recycler_view_classes;

import java.io.Serializable;

public class Estabelecimento implements Serializable {
    private int id;
    private String nome;
    private Categoria categoria;
    private String endereco;
    private String rotaGoogleMaps;
    private String imagem;
    private double avaliacao;
    private String telefoneContato;

    //Construtor Geral da classe
    public Estabelecimento(String nome, Categoria categoria, String endereco, String rotaGoogleMaps, String imagem, double avaliacao, String telefoneContato, boolean favoritado) {
        this.nome = nome;
        this.categoria = categoria;
        this.endereco = endereco;
        this.rotaGoogleMaps = rotaGoogleMaps;
        this.imagem = imagem;
        this.avaliacao = avaliacao;
        this.telefoneContato = telefoneContato;
    }

    //Construtor para a RecyclerView dos estabelecimentos recentemente visitados na tela de inicio.
    public Estabelecimento(String imagem, String nome) {
        this.imagem = imagem;
        this.nome = nome;
    }

    //Construtor para a RecyclerView da lista de estabelecimentos na tela de inicio.
    public Estabelecimento(String imagem, String nome, double avaliacao, Categoria categoria) {
        this.imagem = imagem;
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
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

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", endereco='" + endereco + '\'' +
                ", rotaGoogleMaps='" + rotaGoogleMaps + '\'' +
                ", imagem=" + imagem +
                ", avaliacao=" + avaliacao +
                ", telefoneContato='" + telefoneContato + '\'' +
                '}';
    }
}