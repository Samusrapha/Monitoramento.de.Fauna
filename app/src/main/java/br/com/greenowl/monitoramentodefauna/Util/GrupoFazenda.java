package br.com.greenowl.monitoramentodefauna.Util;



import java.util.ArrayList;

/**
 * Created by Raphael on 04/09/2016.
 */
public class GrupoFazenda {
    private String fazenda;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();
    public String getName() {
        return fazenda;
    }

    public void setName(String name) {
        this.fazenda = name;
    }

    public ArrayList<ChildInfo> getFazendaList() {
        return list;
    }

    public void setFazendaList(ArrayList<ChildInfo> fazendaList) {
        this.list = fazendaList;
    }

}
