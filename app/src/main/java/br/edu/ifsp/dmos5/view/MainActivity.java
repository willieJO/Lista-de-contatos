package br.edu.ifsp.dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UsuarioDaoImplement;
import br.edu.ifsp.dmos5.utils.Utils;
import br.edu.ifsp.dmos5.view.constant.Constant;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    private Button entrar;
    private Button cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findALlElementsById();
        adicionarEventosAosBotoes();
        instanciaBanco();
    }

    private void instanciaBanco() {
        new UsuarioDaoImplement();
    }

    private void findALlElementsById() {
        login = findViewById(R.id.editLogin);
        senha = findViewById(R.id.editSenha);
        entrar = findViewById(R.id.buttonEntrar);
        cadastrar = findViewById(R.id.buttonCadastrar);
    }

    private void adicionarEventosAosBotoes() {
        entrar.setOnClickListener(view -> login());
        cadastrar.setOnClickListener(view -> cadastrar());
    }
        private void login () {
        if (login.getText().toString().length() == 0 || senha.getText().toString().length() == 0) {
            Toast.makeText(this,"Todos os campos são obrigatorios",Toast.LENGTH_LONG).show();
            return;
        }
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, ActivityContatos.class);
        bundle.putString(Constant.Login, login.getText().toString());
        bundle.putString(Constant.Senha, Utils.criptografar(senha.getText().toString()));
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void cadastrar () {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, Activity_Cadastrar.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}