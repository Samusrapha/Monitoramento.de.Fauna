package br.com.greenowl.monitoramentodefauna.Util;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.greenowl.monitoramentodefauna.MainActivity;

/**
 * Created by Raphael on 22/11/2016.
 */
public class DoLogin extends AsyncTask&lt;String,String,String&gt;
        {
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();
@Override
protected void onPreExecute() {
        pbbar.setVisibility(View.VISIBLE);
        }

@Override
protected void onPostExecute(String r) {
        pbbar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this,r,Toast.LENGTH_SHORT).show();

        if(isSuccess) {
        Intent i = new Intent(MainActivity.this, AddProducts.class);
        startActivity(i);
        finish();
        }

        }

@Override
protected String doInBackground(String... params) {
        if(userid.trim().equals("")|| password.trim().equals(""))
        z = "Please enter User Id and Password";
        else
        {
        try {
        Connection con = connectionClass.CONN();
        if (con == null) {
        z = "Error in connection with SQL server";
        } else {
        String query = "select * from Usertbl where UserId='" + userid + "' and password='" + password + "'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next())
        {

        z = "Login successfull";
        isSuccess=true;
        }
        else
        {
        z = "Invalid Credentials";
        isSuccess = false;
        }

        }
        }
        catch (Exception ex)
        {
        isSuccess = false;
        z = "Exceptions";
        }
        }
        return z;
        }
        }