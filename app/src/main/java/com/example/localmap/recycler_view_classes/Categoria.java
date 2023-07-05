package com.example.localmap.recycler_view_classes;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int id;
    private String imagem;
    private String nome;

    public Categoria(String imagem, String nome) {
        this.imagem = imagem;
        this.nome = nome;
    }

    /*Abaixo, construtor para necessitar definir apenas o nome da categoria
    nos itens da lista de estabelecimentos na classe "Estabelecimento".*/
    public Categoria(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", imagem='" + imagem + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}