package com.financaspessoais.financaspessoais;

/**
 * Created by Pedro Paulo on 28/11/2017.
 */

public class ScriptSql {
    public static String getScriptSql(){
        StringBuilder CodigoSql = new StringBuilder();
        CodigoSql.append("CREATE TABLE IF NOT EXISTS TBFINANCA( ");
        CodigoSql.append("IDFINANCA INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,  ");
        CodigoSql.append("VALORRECEITA FLOAT, ");
        CodigoSql.append("VALORDESPESA FLOAT, ");
        CodigoSql.append("DATARECEITA VARCHAR(20), ");
        CodigoSql.append("DATADESPESA VARCHAR(20), ");
        CodigoSql.append("DIARECEITA VARCHAR(5), ");
        CodigoSql.append("DIADESPESA VARCHAR(5), ");
        CodigoSql.append("MESRECEITA VARCHAR(5), ");
        CodigoSql.append("MESDESPESA VARCHAR(5), ");
        CodigoSql.append("ANORECEITA VARCHAR(5), ");
        CodigoSql.append("ANODESPESA VARCHAR(5), ");
        CodigoSql.append("TIPORECEITA VARCHAR(20), ");
        CodigoSql.append("TIPODESPESA VARCHAR(20), ");
        CodigoSql.append("DESCRICAORECEITA VARCHAR(60), ");
        CodigoSql.append("DESCRICAODESPESA VARCHAR(60) ");
        CodigoSql.append(") ");

        return CodigoSql.toString();
    }
}
