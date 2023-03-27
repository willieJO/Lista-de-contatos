package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UsuarioDaoImplement;

public class MainActivity extends AppCompatActivity {
    EditText login;
    EditText senha;
    Button entrar;
    Button cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findALlElementsById();
        adicionarEventosAosBotoes();
        instanciaBanco();
    }

    public void instanciaBanco() {
        new UsuarioDaoImplement();
    }

    @SuppressLint("WrongViewCast")
    public void findALlElementsById() {
        login = findViewById(R.id.editLogin);
        senha = findViewById(R.id.editSenha);
        entrar = findViewById(R.id.buttonEntrar);
        cadastrar = findViewById(R.id.buttonCadastrar);
    }

    public void adicionarEventosAosBotoes() {
        entrar.setOnClickListener(view -> login());
        cadastrar.setOnClickListener(view -> cadastrar());
    }
    public void login () {}
    public void cadastrar () {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, Activity_Cadastrar.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}