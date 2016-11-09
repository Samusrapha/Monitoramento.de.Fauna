package br.com.greenowl.monitoramentodefauna;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import br.com.greenowl.monitoramentodefauna.App.MessageBox;
import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;
import br.com.greenowl.monitoramentodefauna.Dominio.RepositorioEspecie;

public class CadastroEspecie extends AppCompatActivity {



    private EditText edtespecie;
    private EditText edtnomecientifico;
    private EditText edtgenero;
    private EditText edtfamilia;
    private EditText edtordem;
    private EditText edtclasse;

    private br.com.greenowl.monitoramentodefauna.Database.Database Database;
    private SQLiteDatabase Conn;
    private RepositorioEspecie repositorioespecie;
    private RegistrosSpp registros;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_especie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
                finish();
            }
        });



        edtespecie = (EditText) findViewById(R.id.edtspp);
        edtnomecientifico = (EditText) findViewById(R.id.edtnomec);
        edtgenero = (EditText) findViewById(R.id.edtgenero);
        edtfamilia = (EditText) findViewById(R.id.edtfamilia);
        edtordem = (EditText) findViewById(R.id.edtordem);
        edtclasse = (EditText) findViewById(R.id.edtclasse);








        Bundle bundle= getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(Especies.LST_ESPECIES)))
        {
            registros = (RegistrosSpp) bundle.getSerializable(Especies.LST_ESPECIES);
            preencheDados();

        }else
            registros = new RegistrosSpp();


        try {







            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            repositorioespecie = new RepositorioEspecie(Conn);




        }catch (SQLException ex)
        {

            MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);



        if(registros.getId()!=0)
            menu.getItem(1).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.mni_acao1:

                salvar();

                finish();


                break;
            case R.id.mni_acao2:
                excluir ();
                finish();


                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void preencheDados()
    {

        edtespecie.setText(registros.getSPP());
        edtnomecientifico.setText(registros.getNOMEC());
        edtgenero.setText(registros.getGENERO());
        edtfamilia.setText(registros.getFAMILIA());
        edtordem.setText(registros.getORDEM());
        edtclasse.setText(registros.getCLASSE());





    }

    private void excluir ()
    {
        try{
            repositorioespecie.excluir(registros.getId());

        }catch (Exception ex)
        {
            MessageBox.show(this,"Erro","Erro ao excluir  os dados "+ ex.getMessage());


        }
    }

    private void salvar()
    {

        try{




            registros.setSPP(edtespecie.getText().toString());
            registros.setNOMEC(edtnomecientifico.getText().toString());
            registros.setGENERO(edtgenero.getText().toString());
            registros.setFAMILIA(edtfamilia.getText().toString());
            registros.setORDEM(edtordem.getText().toString());
            registros.setCLASSE(edtclasse.getText().toString());






            if  (registros.getId()==0)
            {
                repositorioespecie.inserirspp(registros);
            }else
                repositorioespecie.alterarspp(registros);


        }catch (Exception ex)
        {

            MessageBox.show(this,"Erro","Erro ao salvar  os dados "+ ex.getMessage());

        }
    }







    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "back button pressed");


            new AlertDialog.Builder(this)
                    .setTitle("SALVAR?")
                    .setMessage("Salvar apontamentos?")
                    .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            salvar();

                            finish();
                        }
                    })
                    .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        return super.onKeyDown(keyCode, event);
}}
