package br.com.greenowl.monitoramentodefauna;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.App.MessageBox;
import br.com.greenowl.monitoramentodefauna.App.ViewHelper;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;
import br.com.greenowl.monitoramentodefauna.Dominio.Repositorioformulario;
import br.com.greenowl.monitoramentodefauna.Util.DateUtil;

public class Formulario extends AppCompatActivity {

    private EditText edtdata;
    private EditText edtresponsavel;
    private EditText edtobs;
    private Spinner spnplato;
    private Spinner spnambiente;
    private Spinner spnperiodo;
    private Spinner spnmetodo;
    private Spinner spnespecie;
    private Spinner spncondicoesclimaticas;
    private Spinner spntransecto;

    private ArrayAdapter<String> adpplato;
    private ArrayAdapter<String> adpambiente;
    private ArrayAdapter<String> adpperiodo;
    private ArrayAdapter<String> adpmetodo;
    private ArrayAdapter<String> adpespecie;
    private ArrayAdapter<String> adpcondicoesclimaticas;
    private ArrayAdapter<String> adptransecto;

    private br.com.greenowl.monitoramentodefauna.Database.Database Database;
    private SQLiteDatabase Conn;
    private Repositorioformulario repositorioformulario;
    private Registros registros;


//tentativa de camera
    Button btnTakePhoto;
    ImageView imgTakenPhoto;
    private static final int CAM_REQUEST = 1313;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarf);
        setSupportActionBar(toolbar);
        final String s = getIntent().getStringExtra("TIPO");
        String titulo= getIntent().getStringExtra("TITULO");

        setTitle(titulo);











        ((Button) findViewById(R.id.btnnovo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CadastroEspecie.class);
                startActivityForResult(intent, 0);

            }
        });


        class btnTakePhotoClicker implements Button.OnClickListener
        {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraintent, CAM_REQUEST);
            }

        }


        
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new btnTakePhotoClicker()) ;





        edtdata = (EditText) findViewById(R.id.edtdata);
        edtresponsavel=(EditText) findViewById(R.id.edtresponsavel);
        edtobs=(EditText) findViewById(R.id.edtobs);
        spnplato = (Spinner) findViewById(R.id.spnplato);
        spnambiente = (Spinner) findViewById(R.id.spnambiente);
        spnperiodo = (Spinner) findViewById(R.id.spnperiodo);
        spnmetodo = (Spinner) findViewById(R.id.spnmetodo);
        spnespecie = (Spinner) findViewById(R.id.spnespecie);
        spncondicoesclimaticas=(Spinner) findViewById(R.id.spncondicoesclimaticas);
        spntransecto=(Spinner) findViewById(R.id.spntransecto);



        adpplato = ViewHelper.createArrayAdapter(this, spnplato);
        adpambiente = ViewHelper.createArrayAdapter(this, spnambiente);
        adpperiodo = ViewHelper.createArrayAdapter(this, spnperiodo);
        adpmetodo = ViewHelper.createArrayAdapter(this, spnmetodo);
        adpespecie = ViewHelper.createArrayAdapter(this, spnespecie);
        adpcondicoesclimaticas= ViewHelper.createArrayAdapter(this,spncondicoesclimaticas);
        adptransecto= ViewHelper.createArrayAdapter(this,spntransecto);



        adpplato.add("0- Saracá");
        adpplato.add("1- Papagaio");
        adpplato.add("2- Periquito");
        adpplato.add("3- Aviso");
        adpplato.add("4- Almeidas");
        adpplato.add("6- Monte Branco");
        adpplato.add("7- Cipó");
        adpplato.add("8- Teófilo");
        adpplato.add("9- Aramá");
        adpplato.add("10-Greigh");





        adpambiente.add("0- Encosta");
        adpambiente.add("1- Baixio");
        adpambiente.add("2- Topo");

        adpperiodo.add("0- Estação Seca");
        adpperiodo.add("1- Estação Chuvosa");



        adpmetodo.add("0- Avistamento");
        adpmetodo.add("1- Busca Ativa");
        adpmetodo.add("2- Sherman");
        adpmetodo.add("3-Tomahawk");
        adpmetodo.add("4- Redes de neblina");
        adpmetodo.add("5- Censo de Escuta");

        adpcondicoesclimaticas.add("1-Nublado ");
        adpcondicoesclimaticas.add("2-Chuva Intensa ");
        adpcondicoesclimaticas.add("3-Chuva Moderada ");
        adpcondicoesclimaticas.add("4-Céu Azul ");
        adpcondicoesclimaticas.add("5-Estiagem ");

        adptransecto.add ("T1");
        adptransecto.add ("T2");
        adptransecto.add ("T3");
        adptransecto.add ("T4");


        if(Database == null) {
            Database = new Database(this);
        }
        Conn = Database.getWritableDatabase();
        repositorioformulario = new Repositorioformulario(Conn);
        List<String> lables =repositorioformulario.getAllLabels(getIntent().getStringExtra("GRUPO"));
        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        adpespecie= dataAdapter;
        spnespecie.setAdapter(adpespecie);


        ((Button) findViewById(R.id.btnnovo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CadastroEspecie.class);
                intent.putExtra("GRUPO", getIntent().getStringExtra("GRUPO"));
                startActivityForResult(intent, 0);

            }
        });





        edtdata.setOnClickListener(new Exibedatalistener());
        edtdata.setOnFocusChangeListener(new Exibedatalistener());


        Bundle bundle= getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(Dados.LST_DADOS)))
        {
            registros = (Registros) bundle.getSerializable(Dados.LST_DADOS);
            preencheDados();

        }else
            registros = new Registros();


        try {







            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            repositorioformulario = new Repositorioformulario(Conn);




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


        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format(registros.getDATA());
        edtdata.setText(dt);
        spnplato.setSelection((adpplato.getPosition(registros.getPLATO())));
        spnambiente.setSelection(( adpambiente.getPosition(registros.getAMBIENTE())));
        spnperiodo.setSelection(( adpperiodo.getPosition(registros.getPERIODO())));
        spnmetodo.setSelection(( adpmetodo.getPosition(registros.getMETODO())));
        spnespecie.setSelection(( adpespecie.getPosition(registros.getESPECIE())));
        spntransecto.setSelection((adptransecto.getPosition(registros.getTRANSECTO())));
        edtresponsavel.setText(registros.getRESPONSAVEL());
        edtobs.setText(registros.getOBSERVACAO());
        spncondicoesclimaticas.setSelection((adpcondicoesclimaticas.getPosition(registros.getCONDICOESCLIMATICAS())));




    }

    private void excluir ()
    {
        try{
            repositorioformulario.excluir(registros.getId());

        }catch (Exception ex)
        {
            MessageBox.show(this,"Erro","Erro ao excluir  os dados "+ ex.getMessage());


        }
    }

    private void salvar()
    {

        try{
            registros.setRESPONSAVEL(edtresponsavel.getText().toString());
            registros.setPLATO(String.valueOf(spnplato.getSelectedItem()));
            registros.setPERIODO(String.valueOf(spnperiodo.getSelectedItem()));
            registros.setAMBIENTE(String.valueOf(spnambiente.getSelectedItem()));
            registros.setPERIODO(String.valueOf(spnperiodo.getSelectedItem()));
            registros.setMETODO(String.valueOf(spnmetodo.getSelectedItem()));
            registros.setESPECIE(String.valueOf(spnespecie.getSelectedItem()));
            registros.setCONDICOESCLIMATICAS(String.valueOf(spncondicoesclimaticas.getSelectedItem()));
            registros.setTRANSECTO(String.valueOf(spntransecto.getSelectedItem()));
            registros.setOBSERVACAO(edtobs.getText().toString());
            registros.setTIPO(getIntent().getStringExtra("TIPO"));


            if  (registros.getId()==0)
            {
                repositorioformulario.inserir(registros);
            }else
                repositorioformulario.alterar(registros);


        }catch (Exception ex)
        {

            MessageBox.show(this,"Erro","Erro ao salvar  os dados "+ ex.getMessage());

        }
    }


    private void exibedata()
    {
        Calendar calendar = Calendar.getInstance();


        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this,new SelecionaDataListener(),ano,mes,dia);
        dlg.show();
    }




    private class Exibedatalistener implements View.OnClickListener, View.OnFocusChangeListener
    {

        @Override
        public void onClick(View v) {

            exibedata();

        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus)
                exibedata();

        }
    }

    //--------Salva data
    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String dt = DateUtil.dateToString(year, monthOfYear, dayOfMonth);
            Date data= DateUtil.getDate(year,monthOfYear,dayOfMonth);

            edtdata.setText(dt);

            registros.setDATA(data);

        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d(this.getClass().getName(), "back button pressed");
            if ((edtresponsavel.getText().toString() =="")) {


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
        }
        return super.onKeyDown(keyCode, event);

    }








}







