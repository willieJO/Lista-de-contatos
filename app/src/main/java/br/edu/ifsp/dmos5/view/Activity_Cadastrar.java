package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UsuarioDaoImplement;
import br.edu.ifsp.dmos5.model.Usuario;
import br.edu.ifsp.dmos5.utils.Utils;


public class Activity_Cadastrar extends AppCompatActivity {
    EditText usuario;
    EditText senha;
    EditText confirmaSenha;
    Button cadastrarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findALlElementsById();
        adicionarEventosAosBotoes();
    }

    public void findALlElementsById() {
        usuario = findViewById(R.id.editUsuarioCadastrar);
        senha = findViewById(R.id.editSenhaCadastrar);
        confirmaSenha = findViewById(R.id.editConfirmaSenhaCadastrar);
        cadastrarUsuario = findViewById(R.id.cadastrarUsuario);
    }

    public void adicionarEventosAosBotoes() {
        cadastrarUsuario.setOnClickListener(view -> cadastrarUsuario());
    }

    public void cadastrarUsuario() {
        if (validaCampos() == false) {
            return;
        }
        if (verificaUsuarioRegistradoNoBanco(usuario.getText().toString()) == false) {
            return;
        }
        Usuario user = new Usuario();
        user.setLogin(usuario.getText().toString());
        user.setSenha(Utils.criptografar(senha.getText().toString()));
        UsuarioDaoImplement.database.add(user);
        Context context = getApplicationContext();
        Toast.makeText(context,"Usuario registrado com sucesso",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public boolean verificaUsuarioRegistradoNoBanco(String usuario) {
        if (UsuarioDaoImplement.database.stream()
                .filter(user -> user.getLogin().equals(usuario)).count() > 0) {
            Context context = getApplicationContext();
            Toast.makeText(context,"Usuario já registrado",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean validaCampos() {
        Context context = getApplicationContext();
        if (confirmaSenha.getText().toString().length() == 0 ||
                senha.getText().toString().length() == 0 ||
                usuario.getText().toString().length() == 0) {
            Toast.makeText(context,"Todos os campos são obrigatorios",Toast.LENGTH_LONG).show();
            return false;
        }
        if (!confirmaSenha.getText().toString().equals(senha.getText().toString())) {
            Toast.makeText(context,"As senhas não são iguais",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean validaUsuario() {
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}