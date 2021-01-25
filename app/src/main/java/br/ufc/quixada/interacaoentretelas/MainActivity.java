package br.ufc.quixada.interacaoentretelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    int selected;
    ArrayAdapter adapter;
    ListView listViewPessoa;
    ArrayList<Pessoa> listPessoa;

    EditText getId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;
        listPessoa = new ArrayList<Pessoa>();
        Pessoa p1 = new Pessoa("Emanuel", "Rodrigues");
        listPessoa.add(p1);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listPessoa);
        listViewPessoa = (ListView) findViewById(R.id.listViewListaContatos);
        listViewPessoa.setAdapter(adapter);
    }

    public void goToContact(View view){
        Intent intentPessoa = new Intent(this, ContactActivity.class);
        intentPessoa.putExtra("metodo", "addPessoa");

        startActivityForResult(intentPessoa, Constants.REQUEST_ADD);

        Log.d("MainActivity", "goToContact");
    }

    public void editContact(View view){
        int indexPessoa;
        getId = findViewById(R.id.getPessoaID);
        int id = Integer.parseInt(getId.getText().toString());
        for(indexPessoa = 0; indexPessoa < listPessoa.size(); indexPessoa++){
            if(listPessoa.get(indexPessoa).getId() == id){
                Intent intentEditPessoa = new Intent(this, ContactActivity.class);
                intentEditPessoa.putExtra("metodo", "editContact");
                Pessoa p = listPessoa.get(indexPessoa);
                intentEditPessoa.putExtra("editarPessoa", p);
                intentEditPessoa.putExtra("index", indexPessoa);

                startActivityForResult(intentEditPessoa, Constants.REQUEST_EDIT);
                Log.d("MainActivity", "editContact");
                break;
            }
        }
        if(indexPessoa >= listPessoa.size()) {
            Toast.makeText(this, "ID não encontrado", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
        String logResultado = "Request: " + requestCode + " - " +
                "Result: " + resultCode + "\n";

        // Tenta retornar dados para tela principal apenas se o botao btnAdd por pressionado
        // na tela Contact

        if(requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD ){
            if(data.getExtras() != null){
                Pessoa pessoa =(Pessoa)data.getSerializableExtra("Pessoa");
                listPessoa.add(pessoa);

                String id = new StringBuilder().append(pessoa.getId()).toString();
                String nome = pessoa.getNome();
                String sobrenome = pessoa.getSobrenome();
                logResultado += "[" + id + "]" + " " + nome + " " + sobrenome + "\n";
                Log.d("Log ", logResultado);
            }

        }else if(resultCode == Constants.RESULT_CANCEL){
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        }else if(requestCode == Constants.REQUEST_EDIT &&
                resultCode == Constants.RESULT_EDIT){
            if(data.getExtras() != null){
                Pessoa pessoa =(Pessoa)data.getSerializableExtra("editarPessoa");
                listPessoa.set(data.getExtras().getInt("index"), pessoa);

                String id = new StringBuilder().append(pessoa.getId()).toString();
                String nome = pessoa.getNome();
                String sobrenome = pessoa.getSobrenome();
                logResultado += "Edit[" + id + "]" + " " + nome + " " + sobrenome + "\n";
                Log.d("Log ", logResultado);

            }
        }
    }
}

