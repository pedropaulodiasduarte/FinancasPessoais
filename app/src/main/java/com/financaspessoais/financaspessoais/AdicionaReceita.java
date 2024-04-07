package com.financaspessoais.financaspessoais;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.helpers.ParserFactory;

import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.Date;

import static android.icu.util.Calendar.*;

public class AdicionaReceita extends AppCompatActivity {
    TextView EtDataReceita;
    EditText EtValorReceita;
    TextView EtDescricaoReceita;
    Spinner SpTipoReceita;int MesReceita;
    int DiaReceita;
    int AnoReceita;
    int Dia, Ano, Mes;
    String DataReceita;
    float ValorReceita;
    String DescricaoReceita;
    String TipoReceita;
    BancoDeDados Banco;
    ConsultaScript Consulta;
    private SQLiteDatabase Conexao;

    @TargetApi(Build.VERSION_CODES.N)
    public void Data(){
        Calendar Calendario = Calendar.getInstance();
        AnoReceita = Calendario.get(Calendar.YEAR);
        MesReceita = Calendario.get(Calendar.MONTH);
        MesReceita = MesReceita+1;
    }

    private class SelecionaData implements DatePickerDialog.OnDateSetListener{
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker datePicker, int Ano, int Mes, int Dia) {
            Calendar Calendario = Calendar.getInstance();
            Calendario.set(Ano, Mes, Dia);
            Date Data = Calendario.getTime();
            DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
            String DataFormatada = Formato.format(Data);
            EtDataReceita.setText(DataFormatada);
            MesReceita = Calendario.get(Calendar.MONTH);
            DiaReceita = Calendario.get(Calendar.DAY_OF_MONTH);
            AnoReceita = Calendario.get(Calendar.YEAR);
            //Ano = AnoReceita;
            //Mes = MesReceita;
            //Dia = DiaReceita;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_receita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        

        SpTipoReceita = (Spinner) findViewById(R.id.SpTipoReceita);
        EtDataReceita = (TextView) findViewById(R.id.EtDataReceita);
        EtValorReceita = (EditText) findViewById(R.id.EtValorReceita);
        EtDescricaoReceita = (EditText) findViewById(R.id.EtDescricaoReceita);

        try {
            Banco = new BancoDeDados(this);
            Conexao = Banco.getReadableDatabase();
            Consulta = new ConsultaScript(Conexao);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //ExibeData();
        //EtDataReceita.setText(String.valueOf(Dia)+"/"+String.valueOf(Mes+1)+"/"+String.valueOf(Ano));

        EtDataReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DatePickerDialog Dpd = new DatePickerDialog(AdicionaReceita.this, new SelecionaData(), Ano, Mes, Dia);
                //Dpd.show();
                ExibeData();
            }
        });
        EtDataReceita.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ExibeData();
            }
        });

        ArrayAdapter CriaSelecao = ArrayAdapter.createFromResource(this, R.array.TipoReceita, android.R.layout.simple_spinner_dropdown_item);
        SpTipoReceita.setAdapter(CriaSelecao);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DiaReceita = Dia;
                //MesReceita = Mes;
                //AnoReceita = Ano;
                if (EtValorReceita.getText().length() == 0){
                    Snackbar.make(view, "Campor valor não preenchido", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    ValorReceita = Float.parseFloat(EtValorReceita.getText().toString());
                    DataReceita = EtDataReceita.getText().toString();
                    TipoReceita = SpTipoReceita.getSelectedItem().toString();
                    DescricaoReceita = EtDescricaoReceita.getText().toString();

                    Consulta.InsertReceita(ValorReceita, DataReceita, DiaReceita, MesReceita+1, AnoReceita, TipoReceita, DescricaoReceita);
                    Intent InPrincipal = new Intent(AdicionaReceita.this, Principal.class);
                    startActivity(InPrincipal);
                    finish();
                };

                    //Aqui inserimos os dados na base de dados


            }
        });




    }
    @TargetApi(Build.VERSION_CODES.N)
    private void ExibeData(){
        Calendar Calendario = Calendar.getInstance();
        Ano = Calendario.get(Calendar.YEAR);
        Mes = Calendario.get(Calendar.MONTH);
        Dia = Calendario.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog Dpd = new DatePickerDialog(this, new SelecionaData(), Ano, Mes, Dia);
        Dpd.show();

    }
}
