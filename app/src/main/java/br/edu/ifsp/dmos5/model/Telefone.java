package br.edu.ifsp.dmos5.model;

public class Telefone {
    private String apelido;
    private String nome;
    private String telefone;

    public Telefone(String a,String n, String t) {
        this.apelido = a;
        this.nome = n;
        this.telefone = t;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
