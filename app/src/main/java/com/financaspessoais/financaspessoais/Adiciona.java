package com.financaspessoais.financaspessoais;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Adiciona extends AppCompatActivity {
    Button BtAvancar;
    Button BtCancelar;
    RadioButton RbReceita;
    RadioButton RbDespesa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BtCancelar = (Button) findViewById(R.id.BtCancelar);
        BtAvancar = (Button) findViewById(R.id.BtAvancar);
        RbReceita = (RadioButton) findViewById(R.id.RbReceita);
        RbDespesa = (RadioButton) findViewById(R.id.RbDespesa);
        BtCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InPrincipal = new Intent(Adiciona.this, Principal.class);
                startActivity(InPrincipal);
            }
        });
        BtAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RbDespesa.isChecked()){
                    Intent InAdicionaDespesa = new Intent(Adiciona.this, AdicionaDespesa.class);
                    startActivity(InAdicionaDespesa);
                    finish();
                }
                if (RbReceita.isChecked()){
                    Intent InAdicionaReceita = new Intent(Adiciona.this, AdicionaReceita.class);
                    startActivity(InAdicionaReceita);
                    finish();
                }
            }
        });



    }

}
