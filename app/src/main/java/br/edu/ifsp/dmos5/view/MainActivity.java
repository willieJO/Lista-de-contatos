package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.edu.ifsp.dmos5.R;

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
    public void cadastrar () {}
}