package br.com.greenowl.monitoramentodefauna;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.greenowl.monitoramentodefauna.App.MessageBox;
import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;
import br.com.greenowl.monitoramentodefauna.Dominio.Repositorioformulario;

public class Dados extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    public static final String LST_DADOS = "DADOS";


    private ListView lstdados;
    private ArrayAdapter<Registros> adpdados;
    private br.com.greenowl.monitoramentodefauna.Database.Database Database;
    private br.com.greenowl.monitoramentodefauna.Database.Parse Parse;
    private SQLiteDatabase Conn;
    private Repositorioformulario repositorioRegistros;
    private RegistrosSpp repositorioSpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados);

        lstdados =  (ListView) findViewById(R.id.lstdados);
        lstdados.setOnItemClickListener(this);


        try {
            Database= new Database(this);
            Conn = Database.getWritableDatabase();
            repositorioRegistros = new Repositorioformulario(Conn);
            adpdados = repositorioRegistros.buscaregistros(this);
            lstdados.setAdapter(adpdados);



        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());
        }






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(view.getContext(), Formulario.class);
                startActivityForResult(intent, 0);
            }
        });
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Registros registros = adpdados.getItem(position);

        Intent it = new Intent(this, Formulario.class);
        it.putExtra(LST_DADOS, registros);
        startActivityForResult(it, 0);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpdados = repositorioRegistros.buscaregistros(this);

        lstdados.setAdapter(adpdados);
    }

    @Override
    public void onClick(View view) {

        Intent it = new Intent(this, Formulario.class);
        startActivityForResult(it, 0);
    }


}
