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
    public static String RESPONSAVEL="RESPONSAVEL";
    public static String CONDICOESCLIMATICAS="CONDICOESCLIMATICAS";
    public static String TRANSECTO="TRANSECTO";
    public static String OBSERVACAO="OBSERVACAO";

    private long id;
    private String parcela;
    private Date data;
    private String plato;
    private String ambiente;
    private String periodo;
    private String metodo;
    private String especie;
    private String responsavel;
    private String condicoesclimaticas;
    private String transecto;
    private String observacao;







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

    public String getTRANSECTO() {
        return transecto;
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

    public String getRESPONSAVEL() {
        return responsavel;
    }

    public String getCONDICOESCLIMATICAS() {
        return condicoesclimaticas;
    }

    public String getOBSERVACAO() {
        return observacao;
    }

    public void getPLATO(String plato) {
        this.plato = plato;
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

    public void getRESPONSAVEL(String responsavel){this.responsavel=responsavel;}
    public void setRESPONSAVEL(String responsavel){this.responsavel=responsavel;}

    public void getCONDICOESCLIMATICAS(String condicoesclimaticas){this.condicoesclimaticas=condicoesclimaticas;}
    public void setCONDICOESCLIMATICAS(String condicoesclimaticas){this.condicoesclimaticas=condicoesclimaticas;}

    public void getTRANSECTO(String transecto){this.transecto=transecto;}
    public void setTRANSECTO(String transecto){this.transecto=transecto;}

    public void getOBSERVACAO(String observacao){this.observacao=observacao;}
    public void setOBSERVACAO(String observacao){this.observacao=observacao;}








    @Override

    public String toString() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(data);
       // String dt = DateUtil.dateToString(year, monthOfYear, dayOfMonth);

        return id + "|"+ especie + "|" + dt;
    }

}
