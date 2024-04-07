package com.financaspessoais.financaspessoais;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.database.sqlite.*;
import android.database.*;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Principal extends AppCompatActivity {
    BancoDeDados Banco;
    ConsultaScript Consulta;
    private SQLiteDatabase Conexao;
    TextView TvValorSaldoTotal;
    TextView TvValorReceitas;
    Button BtSetaEsquerda;
    Button BtSetaDireita;
    TextView TvData, TvPcMoradia;
    TextView TvValorDespesas, TvPcEducacao, TvPcLazer, TvPcPagamentos, TvPcRoupa, TvPcSaude, TvPcTransporte, TvPcOutros;
    TextView TvPcAlimentacao;
    int Mes, Ano;

    @TargetApi(Build.VERSION_CODES.N)
    public void Data(){
        Calendar Calendario = Calendar.getInstance();
        Ano = Calendario.get(Calendar.YEAR);
        Mes = Calendario.get(Calendar.MONTH);
        Mes = Mes+1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView TvPcAlimentacao;

        TvValorSaldoTotal = (TextView) findViewById(R.id.TvValorSaldoTotal);
        TvValorReceitas = (TextView) findViewById(R.id.TvValorReceitas);
        TvValorDespesas = (TextView) findViewById(R.id.TvValorDespesa);
        TvPcAlimentacao = (TextView) findViewById(R.id.TvPcAlimentacao);
        BtSetaEsquerda = (Button) findViewById(R.id.BtSetaEsquerda);
        BtSetaDireita = (Button) findViewById(R.id.BtSetaDireita);
        TvData = (TextView) findViewById(R.id.TvData);
        TvPcEducacao = (TextView) findViewById(R.id.TvPcEducacao);
        TvPcLazer = (TextView) findViewById(R.id.TvPcLazer);
        TvPcPagamentos = (TextView) findViewById(R.id.TvPcPagamentos);
        TvPcRoupa = (TextView) findViewById(R.id.TvPcRoupa);
        TvPcSaude = (TextView) findViewById(R.id.TvPcSaude);
        TvPcTransporte = (TextView) findViewById(R.id.TvPcTransporte);
        TvPcOutros = (TextView) findViewById(R.id.TvPcOutros);
        TvPcMoradia = (TextView) findViewById(R.id.TvPcMoradia);

        Data();
        TvData.setText(String.valueOf(Mes)+"/"+String.valueOf(Ano));

        try {
            Banco = new BancoDeDados(this);
            Conexao = Banco.getReadableDatabase();
            Consulta = new ConsultaScript(Conexao);

            TvValorSaldoTotal.setText("R$"+String.valueOf(Float.parseFloat(Consulta.ValorTotalReceita())-Float.parseFloat(Consulta.ValorTotalDespesa())));

            TvValorReceitas.setText("R$"+Consulta.ValorReceita(Mes, Ano));
            TvValorDespesas.setText("R$"+Consulta.ValorDespesa(Mes, Ano));

            if (Float.parseFloat(Consulta.ValorAlimentacao(Mes, Ano)) == 0) {
                TvPcAlimentacao.setText("0%");
            }
            else {
                TvPcAlimentacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorAlimentacao(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorEducacaoo(Mes, Ano)) == 0) {
                TvPcEducacao.setText("0%");
            }
            else {
                TvPcEducacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorEducacaoo(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorLazer(Mes, Ano)) == 0) {
                TvPcLazer.setText("0%");
            }
            else {
                TvPcLazer.setText(String.valueOf((Float.parseFloat(Consulta.ValorLazer(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorMoradia(Mes, Ano)) == 0) {
                TvPcMoradia.setText("0%");
            }
            else {
                TvPcMoradia.setText(String.valueOf((Float.parseFloat(Consulta.ValorMoradia(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorPagamentos(Mes, Ano)) == 0) {
                TvPcPagamentos.setText("0%");
            }
            else {
                TvPcPagamentos.setText(String.valueOf((Float.parseFloat(Consulta.ValorPagamentos(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorRoupa(Mes, Ano)) == 0) {
                TvPcRoupa.setText("0%");
            }
            else {
                TvPcRoupa.setText(String.valueOf((Float.parseFloat(Consulta.ValorRoupa(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorSaude(Mes, Ano)) == 0) {
                TvPcSaude.setText("0%");
            }
            else {
                TvPcSaude.setText(String.valueOf((Float.parseFloat(Consulta.ValorSaude(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorTransporte(Mes, Ano)) == 0) {
                TvPcTransporte.setText("0%");
            }
            else {
                TvPcTransporte.setText(String.valueOf((Float.parseFloat(Consulta.ValorTransporte(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }
            if (Float.parseFloat(Consulta.ValorOutros(Mes, Ano)) == 0) {
                TvPcOutros.setText("0%");
            }
            else {
                TvPcOutros.setText(String.valueOf((Float.parseFloat(Consulta.ValorOutros(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
            }

             /*AlertDialog.Builder Dl = new AlertDialog.Builder(this);
             Dl.setMessage("Conectado");
             Dl.setNeutralButton("OK", null);
             Dl.show();*/
        }
        catch (SQLException ex){
            AlertDialog.Builder Dl = new AlertDialog.Builder(this);
            Dl.setMessage("Conexão com erro" + ex.getMessage());
            Dl.setNeutralButton("OK", null);
            Dl.show();

        }

        BtSetaEsquerda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mes = Mes-1;
                if (Mes==0){
                    Mes = 12;
                    Ano = Ano-1;
                }
                TvData.setText(String.valueOf(Mes)+"/"+String.valueOf(Ano));
                TvValorSaldoTotal.setText("R$"+String.valueOf(Float.parseFloat(Consulta.ValorTotalReceita())-Float.parseFloat(Consulta.ValorTotalDespesa())));

                TvValorReceitas.setText("R$"+Consulta.ValorReceita(Mes, Ano));
                TvValorDespesas.setText("R$"+Consulta.ValorDespesa(Mes, Ano));
                if (Float.parseFloat(Consulta.ValorAlimentacao(Mes, Ano)) == 0) {
                    TvPcAlimentacao.setText("0%");
                }
                else {
                    TvPcAlimentacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorAlimentacao(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }

                if (Float.parseFloat(Consulta.ValorEducacaoo(Mes, Ano)) == 0) {
                    TvPcEducacao.setText("0%");
                }
                else {
                    TvPcEducacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorEducacaoo(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorLazer(Mes, Ano)) == 0) {
                    TvPcLazer.setText("0%");
                }
                else {
                    TvPcLazer.setText(String.valueOf((Float.parseFloat(Consulta.ValorLazer(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorMoradia(Mes, Ano)) == 0) {
                    TvPcMoradia.setText("0%");
                }
                else {
                    TvPcMoradia.setText(String.valueOf((Float.parseFloat(Consulta.ValorMoradia(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorPagamentos(Mes, Ano)) == 0) {
                    TvPcPagamentos.setText("0%");
                }
                else {
                    TvPcPagamentos.setText(String.valueOf((Float.parseFloat(Consulta.ValorPagamentos(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorRoupa(Mes, Ano)) == 0) {
                    TvPcRoupa.setText("0%");
                }
                else {
                    TvPcRoupa.setText(String.valueOf((Float.parseFloat(Consulta.ValorRoupa(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorSaude(Mes, Ano)) == 0) {
                    TvPcSaude.setText("0%");
                }
                else {
                    TvPcSaude.setText(String.valueOf((Float.parseFloat(Consulta.ValorSaude(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorTransporte(Mes, Ano)) == 0) {
                    TvPcTransporte.setText("0%");
                }
                else {
                    TvPcTransporte.setText(String.valueOf((Float.parseFloat(Consulta.ValorTransporte(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorOutros(Mes, Ano)) == 0) {
                    TvPcOutros.setText("0%");
                }
                else {
                    TvPcOutros.setText(String.valueOf((Float.parseFloat(Consulta.ValorOutros(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
            }
        });
        BtSetaDireita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mes = Mes+1;
                if (Mes==13){
                    Mes = 1;
                    Ano = Ano+1;
                }
                TvData.setText(String.valueOf(Mes)+"/"+String.valueOf(Ano));
                TvValorSaldoTotal.setText("R$"+String.valueOf(Float.parseFloat(Consulta.ValorTotalReceita())-Float.parseFloat(Consulta.ValorTotalDespesa())));

                TvValorReceitas.setText("R$"+Consulta.ValorReceita(Mes, Ano));
                TvValorDespesas.setText("R$"+Consulta.ValorDespesa(Mes, Ano));

                if (Float.parseFloat(Consulta.ValorAlimentacao(Mes, Ano)) == 0) {
                    TvPcAlimentacao.setText("0%");
                }
                else {
                    TvPcAlimentacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorAlimentacao(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }

                if (Float.parseFloat(Consulta.ValorEducacaoo(Mes, Ano)) == 0) {
                    TvPcEducacao.setText("0%");
                }
                else {
                    TvPcEducacao.setText(String.valueOf((Float.parseFloat(Consulta.ValorEducacaoo(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorLazer(Mes, Ano)) == 0) {
                    TvPcLazer.setText("0%");
                }
                else {
                    TvPcLazer.setText(String.valueOf((Float.parseFloat(Consulta.ValorLazer(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorMoradia(Mes, Ano)) == 0) {
                    TvPcMoradia.setText("0%");
                }
                else {
                    TvPcMoradia.setText(String.valueOf((Float.parseFloat(Consulta.ValorMoradia(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorPagamentos(Mes, Ano)) == 0) {
                    TvPcPagamentos.setText("0%");
                }
                else {
                    TvPcPagamentos.setText(String.valueOf((Float.parseFloat(Consulta.ValorPagamentos(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorRoupa(Mes, Ano)) == 0) {
                    TvPcRoupa.setText("0%");
                }
                else {
                    TvPcRoupa.setText(String.valueOf((Float.parseFloat(Consulta.ValorRoupa(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorSaude(Mes, Ano)) == 0) {
                    TvPcSaude.setText("0%");
                }
                else {
                    TvPcSaude.setText(String.valueOf((Float.parseFloat(Consulta.ValorSaude(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorTransporte(Mes, Ano)) == 0) {
                    TvPcTransporte.setText("0%");
                }
                else {
                    TvPcTransporte.setText(String.valueOf((Float.parseFloat(Consulta.ValorTransporte(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
                if (Float.parseFloat(Consulta.ValorOutros(Mes, Ano)) == 0) {
                    TvPcOutros.setText("0%");
                }
                else {
                    TvPcOutros.setText(String.valueOf((Float.parseFloat(Consulta.ValorOutros(Mes,Ano)) * 100) / Float.parseFloat(Consulta.ValorDespesa(Mes, Ano))) + "%");
                }
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InEscolha = new Intent(Principal.this, Adiciona.class);
                startActivity(InEscolha);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(Principal.this, Sobre.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
