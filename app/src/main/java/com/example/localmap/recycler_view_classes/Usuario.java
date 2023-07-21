package com.example.localmap.recycler_view_classes;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String contaGoogleId; // Novo campo para armazenar o ID da conta Google

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContaGoogleId() {
        return contaGoogleId;
    }

    public void setContaGoogleId(String contaGoogleId) {
        this.contaGoogleId = contaGoogleId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
