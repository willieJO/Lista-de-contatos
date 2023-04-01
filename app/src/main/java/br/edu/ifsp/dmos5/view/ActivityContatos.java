package br.edu.ifsp.dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.R;
import br.edu.ifsp.dmos5.dao.UsuarioDaoImplement;
import br.edu.ifsp.dmos5.model.Telefone;
import br.edu.ifsp.dmos5.model.Usuario;
import br.edu.ifsp.dmos5.utils.Utils;
import br.edu.ifsp.dmos5.view.adapter.ContatoSpinnerAdapter;
import br.edu.ifsp.dmos5.view.constant.Constant;

public class ActivityContatos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView contatoNome;
    TextView contatoNumero;
    Button criarContato;
    Spinner mSpinner;

    private Usuario usuarioDaSessao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);
        findElementById();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String login = bundle.getString(Constant.Login, "");
            String senha = bundle.getString(Constant.Senha, "");
            boolean contaExiste = false;

            for (Usuario usuario : UsuarioDaoImplement.database) {
                if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
                    contaExiste = true;
                    usuarioDaSessao = UsuarioDaoImplement
                            .database
                            .stream()
                            .filter(x -> x.getLogin().equals(login) && x.getSenha().equals(senha))
                            .findFirst()
                            .orElse(null);
                }
            }
            if (!contaExiste) {
                Toast.makeText(this,"Usuario ou senha errado",Toast.LENGTH_LONG).show();
                finish();
            }

        }
        populateSpinner();
    }

    private void findElementById() {
        criarContato = findViewById(R.id.criaNovoContato);
        contatoNome = findViewById(R.id.nomeDoContato);
        contatoNumero = findViewById(R.id.numeroDoContato);
        mSpinner = findViewById(R.id.spinnerContatos);
        mSpinner.setOnItemSelectedListener(this);
        criarContato.setOnClickListener(view -> criaContato());
    }

    private void criaContato() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, ActivityNovoContato.class);
        bundle.putInt(Constant.UsuarioId,usuarioDaSessao.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateSpinner();
        mSpinner.setSelection(0);
        contatoNome.setText("");
        contatoNumero.setText("");
    }

    @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Telefone tel = (Telefone) mSpinner.getItemAtPosition(position);
        if (tel != null) {
            contatoNome.setVisibility(View.VISIBLE);
            contatoNumero.setVisibility(View.VISIBLE);
            contatoNome.setText(tel.getNome());
            contatoNumero.setText(tel.getTelefone());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void populateSpinner(){
        List<Telefone> dataset = new ArrayList<>(UsuarioDaoImplement.database
                                                    .stream()
                                                    .filter(x -> x.getId() == usuarioDaSessao.getId())
                                                    .findFirst()
                                                    .orElse(null)
                                                    .getTelefones());
        dataset.add(0, null);
        ContatoSpinnerAdapter adapter = new ContatoSpinnerAdapter(this, android.R.layout.simple_spinner_item, dataset);
        mSpinner.setAdapter(adapter);
    }

}