package br.edu.ifsp.dmos5.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static int lastId =0;
    private int id;
    private String login;
    private String senha;
    private List<Telefone> telefones;

    public Usuario () {
        this.setId(lastId + 1);
        lastId++;
        this.telefones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void adicionarContato(Telefone contato) {
        this.telefones.add(contato);
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
}
