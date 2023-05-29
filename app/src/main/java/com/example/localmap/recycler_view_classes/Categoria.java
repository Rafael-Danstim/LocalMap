package com.example.localmap.recycler_view_classes;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int imagem;
    private String nome;

    public Categoria(int imagem, String nome) {
        this.imagem = imagem;
        this.nome = nome;
    }

    /*Abaixo, construtor para necessitar definir apenas o nome da categoria
    nos itens da lista de estabelecimentos na classe "Estabelecimento".*/
    public Categoria(String nome) {
        this.nome = nome;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}