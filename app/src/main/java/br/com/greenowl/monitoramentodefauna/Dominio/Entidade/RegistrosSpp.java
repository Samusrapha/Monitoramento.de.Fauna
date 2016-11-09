package br.com.greenowl.monitoramentodefauna.Dominio.Entidade;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Raphael on 19/10/2016.
 */
public class RegistrosSpp implements Serializable {




    public static String ID = "_id";
    public static String TABELASPP = "ESPECIE";
    public static String SPP = "ESPECIE";
    public static String NOMECIENTIFICO = "NOMECIENTIFICO";
    public static String GENERO = "GENERO";
    public static String FAMILIA = "FAMILIA";
    public static String ORDEM = "ORDEM";
    public static String CLASSE = "CLASSE";





    private long id;
    private String spp;
    private String nomec;
    private String genero;
    private String familia;
    private String ordem;
    private String classe;





    public RegistrosSpp()

    {
        id = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }




    public String getSPP() {
        return spp;
    }

    public void setSPP(String spp) {
        this.spp = spp;
    }

    public String getNOMEC() {
        return nomec;
    }

    public void setNOMEC(String nomec) {
        this.nomec = nomec;
    }


    public String getGENERO() {
        return genero;
    }

    public void setGENERO(String genero) {
        this.genero = genero;
    }


    public String getFAMILIA() {
        return familia;
    }

    public void setFAMILIA(String familia) {
        this.familia = familia;
    }

    public String getORDEM() {
        return ordem;
    }

    public void setORDEM(String ordem) {
        this.ordem = ordem;
    }

    public String getCLASSE() {
        return classe;
    }

    public void setCLASSE(String classe) {
        this.classe = classe;
    }

    @Override

    public String toString() {


        return id + "|"+ spp + "|";
    }

}
