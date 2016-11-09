package br.com.greenowl.monitoramentodefauna.Dominio.Entidade;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import br.com.greenowl.monitoramentodefauna.Util.DateUtil;

/**
 * Created by Raphael on 08/10/2016.
 */
public class Registros  implements Serializable {

    public static String TABELA = "FORMULARIO";
    public static String ID = "_id";
    public static String PARCELA = "PARCELA";
    public static String DATA = "DATA";
    public static String PLATO = "PLATO";
    public static String AMBIENTE = "AMBIENTE";
    public static String PERIODO = "PERIODO";
    public static String METODO = "METODO";
    public static String ESPECIE = "ESPECIE";

    private long id;
    private String parcela;
    private Date data;
    private String plato;
    private String ambiente;
    private String periodo;
    private String metodo;
    private String especie;







    public Registros()

    {
        id = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }





    public String getPARCELA() {
        return parcela;
    }

    public void setPARCELA(String parcela) {
        this.parcela = parcela;
    }


    public Date getDATA() {
        return data;
    }

    public void setDATA(Date data) {
        this.data = data;
    }


    public String getPLATO() {
        return plato;
    }

    public void setPLATO(String plato) {
        this.plato = plato;
    }

    public String getAMBIENTE() {
        return ambiente;
    }

    public void setAMBIENTE(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getPERIODO() {
        return periodo;
    }

    public void setPERIODO(String periodo) {
        this.periodo = periodo;
    }

    public String getMETODO() {
        return metodo;
    }

    public void setMETODO(String metodo) {
        this.metodo = metodo;
    }

    public String getESPECIE() {
        return especie;
    }

    public void setESPECIE(String especie) {
        this.especie = especie;
    }







    @Override

    public String toString() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(data);
       // String dt = DateUtil.dateToString(year, monthOfYear, dayOfMonth);

        return id + "|"+ especie + "|" + dt;
    }

}
