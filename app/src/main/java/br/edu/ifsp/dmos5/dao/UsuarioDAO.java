package br.edu.ifsp.dmos5.dao;

import java.util.List;

import br.edu.ifsp.dmos5.model.Usuario;

public interface UsuarioDAO {

    void addUser(Usuario beer);

    Usuario findByUsername(Usuario user);

    List<Usuario> findAll();

    List<Usuario> findAll(Order order);
}
