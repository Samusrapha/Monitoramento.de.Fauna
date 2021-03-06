package br.com.greenowl.monitoramentodefauna;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import br.com.greenowl.monitoramentodefauna.Database.Database;
import br.com.greenowl.monitoramentodefauna.Database.Parse;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.RegistrosSpp;
import br.com.greenowl.monitoramentodefauna.Dominio.RepositorioEspecie;
import br.com.greenowl.monitoramentodefauna.Dominio.Repositorioformulario;
import br.com.greenowl.monitoramentodefauna.Retrofit.QuestionsActivity;
import br.com.greenowl.monitoramentodefauna.Util.ConnectionClass;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private br.com.greenowl.monitoramentodefauna.Database.Database Database;
    ConnectionClass connectionClass;
    private RepositorioEspecie repositorioEspecie;
    EditText edtuserid,edtpass;
    Button btnlogin;
    ProgressBar pbbar;
    private SQLiteDatabase Conn;
    private ArrayAdapter<RegistrosSpp> adpespecies;
    private ListView lstespecies;
    public int TIPO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO MUDAR TOOLBAR AO ESCOLHER TIPO DE FAUNA, AINDA NÃO FUNCIONA
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//-------- METODO DE CONEXÃO COM BANCO DE DADOS.
        connectionClass = new ConnectionClass(); //&nbsp;//the class file &NBSP FOI SUPRIMIDO PORQUE AINDA NÃO SEI O QUE FAZ
        edtuserid = (EditText) findViewById(R.id.email);
        edtpass = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.email_sign_in_button);
        //pbbar = (ProgressBar) findViewById(R.id.pbbar);
        //pbbar.setVisibility(View.GONE);






//------ BOTOES

        ((ImageButton)findViewById(R.id.ibav)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(v.getContext(), Faunas.class);
                intent.putExtra("TIPO","AVALIAÇÃO");

                startActivityForResult(intent, 0);

            }
        });

        ((ImageButton)findViewById(R.id.ibrec)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Faunas.class);
                intent.putExtra("TIPO","RECUPERAÇÃO");

                startActivityForResult(intent, 0);

            }
        });
        ((ImageButton)findViewById(R.id.ibdados)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Dados.class);
                startActivityForResult(intent, 0);
            }
        });




            //------------ ao clicar na lista....








        //------------------------
        //botão floating
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Formulario.class);
                startActivityForResult(intent, 0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.importar) {
            Parse importar = new Parse();
            ArrayList<RegistrosSpp> especies = importar.Importardados(this.getApplicationContext());
            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            RepositorioEspecie dbxml = new RepositorioEspecie(Conn);
            for (int i = 0; i < especies.size(); i++) {
                dbxml.inserirspp(especies.get(i));
            }
            Toast.makeText(this, "Especies importadas com sucesso", Toast.LENGTH_SHORT);
        }

            else if (id == R.id.exportar) {

            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            Repositorioformulario dbxml = new Repositorioformulario(Conn);
            ArrayAdapter<Registros> registro = dbxml.buscaregistros(this);
            Parse exportar = new Parse();
            ArrayList<Registros> Lregistro = exportar.AdaptertoList(registro);
            exportar.Exportarxml(Lregistro);
            Toast.makeText(this, "Exportado com sucess XD", Toast.LENGTH_SHORT);

        } else if (id == R.id.exportcloud) {
            Database = new Database(this);
            Conn = Database.getWritableDatabase();
            Repositorioformulario dbxml = new Repositorioformulario(Conn);
            ArrayAdapter<Registros> registro = dbxml.buscaregistros(this);
            Parse exportar = new Parse();
            QuestionsActivity cloud= new QuestionsActivity();
            ArrayList<Registros> Lregistro = exportar.AdaptertoList(registro);
            cloud.Exportaplanilha(Lregistro);
            Toast.makeText(this, "Exportado com sucess XD", Toast.LENGTH_SHORT);



        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
