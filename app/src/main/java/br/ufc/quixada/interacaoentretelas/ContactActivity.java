package br.ufc.quixada.interacaoentretelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {
    EditText edtNome;
    EditText edtSobrenome;
    TextView idTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //Pegando Extra da Main
        String methodCalled = (String) getIntent().getExtras().get("metodo");
        Log.d("metodo:", methodCalled);

        idTextView = findViewById(R.id.textViewId);
        edtNome = findViewById(R.id.getNome);
        edtSobrenome = findViewById(R.id.getSobrenome);

        if(methodCalled.equals("editContact")){
            Pessoa editP = (Pessoa)getIntent().getSerializableExtra("editarPessoa");
            String id = new StringBuilder().append(editP.getId()).toString();
            String nome = editP.getNome();
            String sobrenome = editP.getSobrenome();
            idTextView.setText(id);
            edtNome.setText(nome);
            edtSobrenome.setText(sobrenome);
        }



    }

    public void confirm(View view) {
        String methodCalled = (String) getIntent().getExtras().get("metodo");
        if(methodCalled.equals("editContact")){
            //Intent intentEditPessoa = new Intent();
            Pessoa editPessoa = (Pessoa)getIntent().getSerializableExtra("editarPessoa");
            editPessoa.setNome(edtNome.getText().toString());
            editPessoa.setSobrenome(edtSobrenome.getText().toString());
            getIntent().putExtra("editarPessoa", editPessoa);
            setResult(Constants.RESULT_EDIT, getIntent());

            Log.d("ContactActivity", "editPessoa");

        }else{
            Intent intentAddPessoa = new Intent();
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(edtNome.getText().toString());
            pessoa.setSobrenome(edtSobrenome.getText().toString());
            intentAddPessoa.putExtra("Pessoa", pessoa);
            setResult(Constants.RESULT_ADD, intentAddPessoa);

            Log.d("ContactActivity", "addPessoa");
        }
        finish();
    }

    public void cancel(View view) {
        setResult(Constants.RESULT_CANCEL);
        finish();

        Log.d("ContactActivity", "cancel");
    }

}

