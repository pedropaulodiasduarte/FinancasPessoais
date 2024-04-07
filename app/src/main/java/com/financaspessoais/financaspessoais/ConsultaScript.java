package com.financaspessoais.financaspessoais;

/**
 * Created by Pedro Paulo on 02/12/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.*;
import android.widget.*;
        ;
public class ConsultaScript {
    SQLiteDatabase Conexao;

    public ConsultaScript(SQLiteDatabase Conexao) {
        this.Conexao = Conexao;
    }

    public void InsertReceita(float ValorReceita, String DataReceita, int DiaReceita, int MesReceita, int AnoReceita, String TipoReceita, String DescricaoReceita) {
        ContentValues Insert = new ContentValues();
        Insert.put("VALORRECEITA", ValorReceita);
        Insert.put("DATARECEITA", DataReceita);
        Insert.put("DIARECEITA", DiaReceita);
        Insert.put("MESRECEITA", MesReceita);
        Insert.put("ANORECEITA", AnoReceita);
        Insert.put("TIPORECEITA", TipoReceita);
        Insert.put("DESCRICAORECEITA", DescricaoReceita);
        Conexao.insert("TBFINANCA", null, Insert);
    }
    public void InsertDespesa(float ValorDespesa, String DataDespesa, int DiaDespesa, int MesDespesa, int AnoDespesa, String TipoDespesa, String DescricaoDespesa){
        ContentValues Insert = new ContentValues();
        Insert.put("VALORDESPESA", ValorDespesa);
        Insert.put("DATADESPESA", DataDespesa);
        Insert.put("DIADESPESA", DiaDespesa);
        Insert.put("MESDESPESA", MesDespesa);
        Insert.put("ANODESPESA", AnoDespesa);
        Insert.put("TIPODESPESA", TipoDespesa);
        Insert.put("DESCRICAODESPESA", DescricaoDespesa);
        Conexao.insert("TBFINANCA", null, Insert);
    }


    public String ValorTotalReceita() {
        String Select = "SELECT SUM(VALORRECEITA) FROM TBFINANCA";
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Receita;
        if (Consulta.moveToFirst()) {
            Receita = Consulta.getFloat(0);
            return String.valueOf(Receita);
        } else {
            return "Erro";
        }
    }
    public String ValorTotalDespesa() {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA";
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Despesa;
        if (Consulta.moveToFirst()) {
            Despesa = Consulta.getFloat(0);
            return String.valueOf(Despesa);
        }
        else {
            return "Erro";
        }
    }

    public String ValorReceita(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORRECEITA) FROM TBFINANCA WHERE MESRECEITA="+String.valueOf(Mes)+" AND ANORECEITA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Alimentacao;
        if (Consulta.moveToFirst()) {
            Alimentacao = Consulta.getFloat(0);
            return String.valueOf(Alimentacao);
        }
        else {
            return "Erro";
        }
    }

    public String ValorDespesa(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Alimentacao;
        if (Consulta.moveToFirst()) {
            Alimentacao = Consulta.getFloat(0);
            return String.valueOf(Alimentacao);
        }
        else {
            return "Erro";
        }
    }

    public String ValorAlimentacao(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Alimentação' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Alimentacao;
        if (Consulta.moveToFirst()) {
            Alimentacao = Consulta.getFloat(0);
            return String.valueOf(Alimentacao);
        }
        else {
            return "Erro";
        }
    }

    public String ValorEducacaoo(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Educação' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }

    public String ValorLazer(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Lazer' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorPagamentos(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Pagamentos' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorRoupa(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Roupa' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorSaude(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Saúde' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorTransporte(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Transporte' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorOutros(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Outros' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
    public String ValorMoradia(int Mes, int Ano) {
        String Select = "SELECT SUM(VALORDESPESA) FROM TBFINANCA WHERE TIPODESPESA='Moradia' AND MESDESPESA="+String.valueOf(Mes)+" AND ANODESPESA="+String.valueOf(Ano);
        Cursor Consulta = Conexao.rawQuery(Select, null);
        float Valor;
        if (Consulta.moveToFirst()) {
            Valor = Consulta.getFloat(0);
            return String.valueOf(Valor);
        }
        else {
            return "Erro";
        }
    }
}