package com.example.localmap.recycler_view_classes;

public class UsuarioEstabelecimentoFavoritado {

    private int id;
    private Usuario usuario;
    private Estabelecimento estabelecimento;
    private boolean favoritado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public boolean isFavoritado() {
        return favoritado;
    }

    public void setFavoritado(boolean favoritado) {
        this.favoritado = favoritado;
    }

    @Override
    public String toString() {
        return "UsuarioEstabelecimento{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", estabelecimento=" + estabelecimento +
                ", favoritado=" + favoritado +
                '}';
    }
}