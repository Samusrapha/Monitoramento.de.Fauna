package br.com.greenowl.monitoramentodefauna.Database;

import br.com.greenowl.monitoramentodefauna.App.MessageBox;

/**
 * Created by Raphael on 03/03/2016.
 */
public class scriptsql {

    public static String getCreateFormulario()
    {
        StringBuilder sqlbuilder=new StringBuilder();
        sqlbuilder.append ("CREATE TABLE IF NOT EXISTS FORMULARIO ( ");
        sqlbuilder.append ("_id                INTEGER       NOT NULL ");
        sqlbuilder.append ("PRIMARY KEY AUTOINCREMENT, ");
        sqlbuilder.append ("PARCELA           VARCHAR (14), ");
        sqlbuilder.append ("DATA           DATE, ");
        sqlbuilder.append ("PLATO       VARCHAR (100), ");
        sqlbuilder.append ("AMBIENTE       VARCHAR (100), ");
        sqlbuilder.append ("PERIODO       VARCHAR (100), ");
        sqlbuilder.append ("METODO       VARCHAR (100), ");
        sqlbuilder.append ("ESPECIE       VARCHAR (1) ");
        sqlbuilder.append (");");


        return sqlbuilder.toString();
    }
    public static String getCreateEspecie()
    {
        StringBuilder sqlbuilder=new StringBuilder();

        sqlbuilder.append ("CREATE TABLE IF NOT EXISTS ESPECIE ( ");
        sqlbuilder.append ("_id                INTEGER       NOT NULL ");
        sqlbuilder.append ("PRIMARY KEY AUTOINCREMENT, ");
        sqlbuilder.append ("ESPECIE VARCHAR (14), ");
        sqlbuilder.append ("NOMECIENTIFICO       VARCHAR (100), ");
        sqlbuilder.append ("GENERO       VARCHAR (100), ");
        sqlbuilder.append ("FAMILIA       VARCHAR (100), ");
        sqlbuilder.append ("ORDEM       VARCHAR (100), ");
        sqlbuilder.append ("CLASSE       VARCHAR (100)");
        sqlbuilder.append (");");




        return sqlbuilder.toString();
}

}


