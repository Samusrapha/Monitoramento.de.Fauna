package br.com.greenowl.monitoramentodefauna.Util;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Raphael on 09/11/2016.
 */
public class ConnectionClass {

    String ip = "192.168.0.100";
    String classs = "net.sourceforge.jtds.jdbc.Driver";
    String db = "Andro";
    String un = "sa";
    String password = "200522";



    @SuppressLint("NewApi")
    public Connection CONN() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {

            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip + ";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}


//TODO CRIADO CLASSE DE CONEXÃO, FALTA INSERIR OS VINCULOS NO MAIN, OU CRIAR UMA NOVA ACTIVITY