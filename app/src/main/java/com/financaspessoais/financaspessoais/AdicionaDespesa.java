package com.financaspessoais.financaspessoais;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class AdicionaDespesa extends AppCompatActivity {
    TextView EtDataDespesa;
    EditText EtValorDespesa;
    EditText EtDescricaoDespesa;
    BancoDeDados Banco;
    ConsultaScript Consulta;
    private SQLiteDatabase Conexao;
    Spinner SpTipoDespesa;
    int Dia, Mes, Ano;
    float ValorDespesa;
    String DataDespesa;
    String DescricaoDespesa;
    String TipoDespesa;
    int MesDespesa = 0;
    int DiaDespesa;
    int AnoDespesa;
    private class SelecionaData implements DatePickerDialog.OnDateSetListener{
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker datePicker, int Ano, int Mes, int Dia) {
            Calendar Calendario = Calendar.getInstance();
            Calendario.set(Ano, Mes, Dia); /* nessas linha cria-se o objeto Calendar, e trás a ele alguma data,
            que veio por parametro */
            Date Data = Calendario.getTime();
            DateFormat Formato = DateFormat.getDateInstance(DateFormat.SHORT);
            String DataFormatada = Formato.format(Data);
            EtDataDespesa.setText(DataFormatada); //Nessas linhas cria um formato de data, a data e coloca no Edit
            MesDespesa = Calendario.get(Calendar.MONTH);
            //Ano = AnoDespesa;
            //Mes = MesDespesa;
            //Dia = DiaDespesa;

            /*Soma-se *1 para ficar o Mes certo, poís desse jeito se o mes for 10,
            ele essta retornando 09 */
            DiaDespesa = Calendario.get(Calendar.DAY_OF_MONTH);
            AnoDespesa = Calendario.get(Calendar.YEAR); //Pega a data para o uso no banco de dados
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_despesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EtDataDespesa = (TextView) findViewById(R.id.EtDataDespesa);
        SpTipoDespesa = (Spinner) findViewById(R.id.SpTipoDespesa);
        EtValorDespesa = (EditText) findViewById(R.id.EtValorDespesa);
        EtDescricaoDespesa = (EditText) findViewById(R.id.EtDescricaoDespesa);

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

        ArrayAdapter CriaSelecao = ArrayAdapter.createFromResource(this, R.array.TipoDespesa, android.R.layout.simple_spinner_dropdown_item);
        SpTipoDespesa.setAdapter(CriaSelecao);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //ExibeData();
        //EtDataDespesa.setText(String.valueOf(Dia)+"/"+String.valueOf(Mes+1)+"/"+String.valueOf(Ano));

        EtDataDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DatePickerDialog Dpd = new DatePickerDialog(AdicionaDespesa.this, new SelecionaData(), Ano, Mes, Dia);
                //Dpd.show();
                ExibeData();
            }
        });
        EtDataDespesa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                ExibeData();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DiaDespesa = Dia;
                //MesDespesa = Mes;
                //AnoDespesa = Ano;
                if (EtValorDespesa.getText().length() == 0){
                    Snackbar.make(view, "Campor valor não preenchido", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    ValorDespesa = Float.parseFloat(EtValorDespesa.getText().toString());
                    DataDespesa = EtDataDespesa.getText().toString();
                    DescricaoDespesa = EtDescricaoDespesa.getText().toString();
                    TipoDespesa = SpTipoDespesa.getSelectedItem().toString();
                    Consulta.InsertDespesa(ValorDespesa, DataDespesa, DiaDespesa, MesDespesa+1, AnoDespesa, TipoDespesa, DescricaoDespesa);
                    Intent InPrincipal = new Intent(AdicionaDespesa.this, Principal.class);
                    startActivity(InPrincipal);
                    finish();
                };
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.N)
    private void ExibeData(){
        Calendar Calendario = Calendar.getInstance(); //Cira o objeto Calendar e faz sua instancia
        Ano = Calendario.get(Calendar.YEAR);
        Mes = Calendario.get(Calendar.MONTH);
        Dia = Calendario.get(Calendar.DAY_OF_MONTH); //Essas linhas pega o mês, ano e dia
        DatePickerDialog Dpd = new DatePickerDialog(AdicionaDespesa.this, new SelecionaData(), Ano, Mes, Dia); /*Nessa linha cria-se
         um objeto DatePickerDialog, que abre uma janela com um calendario, por cima dessa Activity,
         também o SelecionaData(), que devolve o formato de data e data selecionada, tambem envia o mês, ano, dia
         para abrir na data atual*/
        Dpd.show();

    }

}
