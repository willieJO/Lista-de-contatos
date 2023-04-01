package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UsuarioDaoImplement;
import br.edu.ifsp.dmos5.model.Telefone;
import br.edu.ifsp.dmos5.model.Usuario;
import br.edu.ifsp.dmos5.view.constant.Constant;

public class ActivityNovoContato extends AppCompatActivity {
    int usuarioId; // variavel compartilhada entr as Activity
    Button buttonSalvarContato;
    EditText nome;
    EditText apelido;
    EditText telefone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            usuarioId = bundle.getInt(Constant.UsuarioId);
        }

        findALlElementsById();

    }

    private void findALlElementsById() {
        nome = findViewById(R.id.editNome);
        apelido = findViewById(R.id.editApelido);
        telefone = findViewById(R.id.editTelefone);
        buttonSalvarContato = findViewById(R.id.buttonSalvarContato);
        buttonSalvarContato.setOnClickListener(view -> salvarContato());
    }
    private void salvarContato() {
         List<Telefone> telefonesDesseContato = UsuarioDaoImplement.database
                                                      .stream()
                                                      .filter(x -> x.getId() == usuarioId)
                                                      .map(Usuario::getTelefones)
                                                      .findFirst()
                                                      .orElse(null);

        if (telefonesDesseContato.stream()
                .filter(x-> x.getApelido().equals(apelido.getText().toString()))
                .count() > 0) {
            Toast.makeText(this, "Esse apelido ja existe nos seus contatos", Toast.LENGTH_SHORT).show();
            return;
        }

        UsuarioDaoImplement.database.stream()
                .filter(x -> x.getId() == usuarioId)
                .findFirst()
                .orElse(null)
                .adicionarContato((new Telefone(
                        apelido.getText().toString(),
                        nome.getText().toString(),
                        telefone.getText().toString()
                )));
        Toast.makeText(this, "Contato adicionado com sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}