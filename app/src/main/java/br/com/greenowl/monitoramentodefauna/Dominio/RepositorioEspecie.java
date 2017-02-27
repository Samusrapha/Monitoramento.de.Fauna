package br.com.greenowl.monitoramentodefauna.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.Date;

import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;

/**
 * Created by Raphael on 19/10/2016.
 */
public class RepositorioEspecie {


    private SQLiteDatabase conn;

    public RepositorioEspecie(SQLiteDatabase conn)
    {
        this.conn=conn;
    }


    /**
     * METODO ALTERAR NO BANCO
     */

    private ContentValues preencheContentValues (RegistrosSpp registros){

        ContentValues values = new ContentValues();

        values.put(RegistrosSpp.SPP, registros.getSPP());
        values.put(RegistrosSpp.NOMECIENTIFICO, registros.getNOMEC());
        values.put(RegistrosSpp.GENERO, registros.getGENERO());
        values.put(RegistrosSpp.FAMILIA, registros.getFAMILIA());
        values.put(RegistrosSpp.ORDEM, registros.getORDEM());
        values.put(RegistrosSpp.CLASSE, registros.getCLASSE());
        values.put(RegistrosSpp.GRUPO, registros.getGRUPO());





        return values;

    }



    public void excluir (long id)
    {
        conn.delete("ESPECIE", " _id =?", new String[]{String.valueOf(id)});
    }

    /**
     * JOGA preencheContentValues no alterar para update
     */


    public void alterarspp (RegistrosSpp registros){

        ContentValues values = preencheContentValues(registros);

        conn.update(RegistrosSpp.TABELASPP, values, "_id =?", new String[]{String.valueOf(registros.getId())});

    }


    public void inserirspp (RegistrosSpp registros){

        ContentValues values = preencheContentValues(registros);

        conn.insertOrThrow(RegistrosSpp.TABELASPP, null, values);

    }

    public ArrayAdapter<RegistrosSpp> buscaregistros(Context context )
    {
        ArrayAdapter<RegistrosSpp> adpregistros = new ArrayAdapter<RegistrosSpp>( context, android.R.layout.simple_list_item_1);



        Cursor cursor = conn.query(RegistrosSpp.TABELASPP,null,null,null,null,null,null);

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();

            do {

                RegistrosSpp registros = new RegistrosSpp();
                registros.setId(cursor.getLong(cursor.getColumnIndex(RegistrosSpp.ID)));
                registros.setSPP(cursor.getString(cursor.getColumnIndex(RegistrosSpp.SPP)));
                registros.setNOMEC(cursor.getString(cursor.getColumnIndex(RegistrosSpp.NOMECIENTIFICO)));
                registros.setGENERO(cursor.getString(cursor.getColumnIndex(RegistrosSpp.GENERO)));
                registros.setFAMILIA(cursor.getString(cursor.getColumnIndex(RegistrosSpp.FAMILIA)));
                registros.setORDEM(cursor.getString(cursor.getColumnIndex(RegistrosSpp.ORDEM)));
                registros.setCLASSE(cursor.getString(cursor.getColumnIndex(RegistrosSpp.CLASSE)));
                registros.setGRUPO(cursor.getString(cursor.getColumnIndex(RegistrosSpp.GRUPO)));




                adpregistros.add(registros);

            }while (cursor.moveToNext());
        }
        return adpregistros;





    }

}
