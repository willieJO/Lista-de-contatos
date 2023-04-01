package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.model.Usuario;

public class UsuarioDaoImplement implements  UsuarioDAO{
    public static List<Usuario> database;

    public UsuarioDaoImplement() {
        if (database == null) {
            database = new ArrayList<Usuario>();
        }
    }

    @Override
    public void addUser(Usuario user) {
        if (user != null) {
            database.add(user);
        }
    }

    @Override
    public Usuario findByUsername(Usuario user) {
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        return database;
    }



}
