package br.com.greenowl.monitoramentodefauna.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;

/**
 * Created by Raphael on 05/03/2016.
 */
public class Repositorioformulario  {

    private Database database;
    private SQLiteDatabase conn;

    public Repositorioformulario(SQLiteDatabase conn)
    {
        this.conn=conn;
    }


    /**
     * METODO ALTERAR NO BANCO
     */

    private ContentValues preencheContentValues (Registros registros){

        ContentValues values = new ContentValues();

        values.put(Registros.TRANSECTO, registros.getTRANSECTO());
        values.put(Registros.RESPONSAVEL, registros.getRESPONSAVEL());
        values.put(Registros.OBSERVACAO, registros.getOBSERVACAO());
        values.put(Registros.CONDICOESCLIMATICAS, registros.getCONDICOESCLIMATICAS());
        values.put(Registros.DATA, registros.getDATA().getTime());
        values.put(Registros.PLATO, registros.getPLATO());
        values.put(Registros.AMBIENTE, registros.getAMBIENTE());
        values.put(Registros.PERIODO, registros.getPERIODO());
        values.put(Registros.METODO, registros.getMETODO());
        values.put(Registros.ESPECIE, registros.getESPECIE());


        return values;

    }



    public void excluir (long id)
    {
        conn.delete("FORMULARIO", " _id =?", new String[]{String.valueOf(id)});
    }

    /**
     * JOGA preencheContentValues no alterar para update
     */
    public void alterar (Registros registros){

        ContentValues values = preencheContentValues(registros);

        conn.update(Registros.TABELA, values, "_id =?", new String[]{String.valueOf(registros.getId())});

    }

    public void alterarspp (Registros registros){

        ContentValues values = preencheContentValues(registros);

        conn.update(RegistrosSpp.TABELASPP, values, "_id =?", new String[]{String.valueOf(registros.getId())});

    }

    /**
     * JOGA preencheContentValues no alterar para insert
     */
    public void inserir (Registros registros){

        ContentValues values = preencheContentValues(registros);

        conn.insertOrThrow(Registros.TABELA, null, values);

    }


    public ArrayAdapter<Registros> buscaregistros(Context context )
    {
        ArrayAdapter<Registros> adpregistros = new ArrayAdapter<Registros>( context, android.R.layout.simple_list_item_1);



        Cursor cursor = conn.query(Registros.TABELA, null, null, null, null, null, null, null);

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();

            do {

            Registros registros = new Registros();
                registros.setId(cursor.getLong(cursor.getColumnIndex(Registros.ID)));
                registros.setPLATO(cursor.getString(cursor.getColumnIndex(Registros.PLATO)));
                registros.setAMBIENTE(cursor.getString(cursor.getColumnIndex(Registros.AMBIENTE)));
                registros.setPERIODO(cursor.getString(cursor.getColumnIndex(Registros.PERIODO)));
                registros.setMETODO(cursor.getString(cursor.getColumnIndex(Registros.METODO)));
                registros.setESPECIE(cursor.getString(cursor.getColumnIndex(Registros.ESPECIE)));
                registros.setDATA(new Date(cursor.getLong(cursor.getColumnIndex(Registros.DATA))));
                registros.setRESPONSAVEL(cursor.getString(cursor.getColumnIndex(Registros.RESPONSAVEL)));
                registros.setTRANSECTO(cursor.getString(cursor.getColumnIndex(Registros.TRANSECTO)));
                registros.setOBSERVACAO(cursor.getString(cursor.getColumnIndex(Registros.OBSERVACAO)));
                registros.setCONDICOESCLIMATICAS(cursor.getString(cursor.getColumnIndex(Registros.CONDICOESCLIMATICAS)));



                adpregistros.add(registros);

                }while (cursor.moveToNext());
        }
        return adpregistros;

    }


    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM ESPECIE" ;

        Cursor cursor = conn.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        conn.close();

        // returning lables
        return labels;
    }




}
