package br.com.greenowl.monitoramentodefauna.Database;

/**
 * Created by Raphael on 03/03/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.greenowl.monitoramentodefauna.App.MessageBox;

public class Database extends SQLiteOpenHelper{

    public Database(Context context)
    {
        super(context, "COLETOR ",null,1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scriptsql.getCreateFormulario());
        db.execSQL(scriptsql.getCreateEspecie());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Cursor adddemo(){


        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM ESPECIE", null);


            }

    //TODO CURSOR QUE BUSCA OS DADOS

    public ArrayList<String> getAllProvinces(){

        ArrayList<String> list=new ArrayList<String>();
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();


        try
        {

            String selectQuery = "SELECT * FROM ESPECIE";
            Cursor cursor = db.rawQuery(selectQuery, null);
            list.add("asd");
            if(cursor.getCount() >0)

            {
                while (cursor.moveToNext()) {
                    // Add province name to arraylist
                    String pname= cursor.getString(cursor.getColumnIndex("pname"));
                    list.add(pname);
                }
            }
            db.setTransactionSuccessful();
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();

            // Close database
        }
        return list;


    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM ESPECIE" ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }




}
;

