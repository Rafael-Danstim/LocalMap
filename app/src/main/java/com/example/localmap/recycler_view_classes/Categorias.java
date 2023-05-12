package com.example.localmap.recycler_view_classes;

public class Categorias {

    private int imagemDaCategoria;
    private String nomeDaCategoria;

    public Categorias(int imagemDaCategoria, String nomeDaCategoria) {
        this.imagemDaCategoria = imagemDaCategoria;
        this.nomeDaCategoria = nomeDaCategoria;
    }

    public int getImagemDaCategoria() {
        return imagemDaCategoria;
    }

    public void setImagemDaCategoria(int imagemDaCategoria) {
        this.imagemDaCategoria = imagemDaCategoria;
    }

    public String getNomeDaCategoria() {
        return nomeDaCategoria;
    }

    public void setNomeDaCategoria(String nomeDaCategoria) {
        this.nomeDaCategoria = nomeDaCategoria;
    }
}