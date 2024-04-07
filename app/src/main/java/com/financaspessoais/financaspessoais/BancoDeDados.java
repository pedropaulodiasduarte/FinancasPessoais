package com.financaspessoais.financaspessoais;

/**
 * Created by Pedro Paulo on 24/11/2017.
 */
import android.content.Context;
import android.database.sqlite.*;
public class BancoDeDados extends SQLiteOpenHelper {
    public BancoDeDados (Context context){
        super(context, "FINANCAS", null, 2);


    }
    @Override
    public void onCreate(SQLiteDatabase Bd) {
        Bd.execSQL(ScriptSql.getScriptSql());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
