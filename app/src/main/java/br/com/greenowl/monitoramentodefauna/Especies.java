package br.com.greenowl.monitoramentodefauna;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.greenowl.monitoramentodefauna.App.MessageBox;
import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;
import br.com.greenowl.monitoramentodefauna.Dominio.RepositorioEspecie;

public class Especies extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    public static final String LST_ESPECIES = "ESPECIES";


    private ListView lstespecies;
    private ArrayAdapter<RegistrosSpp> adpespecies;
    private br.com.greenowl.monitoramentodefauna.Database.Database Database;
    private br.com.greenowl.monitoramentodefauna.Database.Parse Parse;
    private SQLiteDatabase Conn;
    private RepositorioEspecie repositorioEspecie;
    private RegistrosSpp repositorioSpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especies);

        lstespecies =  (ListView) findViewById(R.id.lstespecie);
        lstespecies.setOnItemClickListener(this);


        try {
            Database= new Database(this);
            Conn = Database.getWritableDatabase();
            repositorioEspecie = new RepositorioEspecie(Conn);
            adpespecies = repositorioEspecie.buscaregistros(this);
            lstespecies.setAdapter(adpespecies);



        } catch (SQLException ex) {
            MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RegistrosSpp registrosSpp = adpespecies.getItem(position);

        Intent it = new Intent(this, CadastroEspecie.class);
        it.putExtra(LST_ESPECIES, registrosSpp);
        startActivityForResult(it, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpespecies = repositorioEspecie.buscaregistros(this);

        lstespecies.setAdapter(adpespecies);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, Especies.class);
        startActivityForResult(it, 0);
    }


}
