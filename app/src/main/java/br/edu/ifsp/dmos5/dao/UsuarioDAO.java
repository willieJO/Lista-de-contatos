package br.edu.ifsp.dmos5.dao;

import java.util.List;

import br.edu.ifsp.dmos5.model.Usuario;

public interface UsuarioDAO {

    public void addUser(Usuario beer);

    public Usuario findByUsername(Usuario user);

    public List<Usuario> findAll();

}
