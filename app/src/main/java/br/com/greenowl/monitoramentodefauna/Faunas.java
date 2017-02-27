package br.com.greenowl.monitoramentodefauna;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.Normalizer;

public class Faunas extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faunas);

        String titulo= getIntent().getStringExtra("TIPO");

        setTitle(titulo);


        ((ImageButton)findViewById(R.id.ibave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                Intent tit = new Intent (v.getContext(),Formulario.class);
              //  intent.putExtra("TIPO",s);
                intent.putExtra("TITULO","AVIFAUNA");
                intent.putExtra("GRUPO","AVIF");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));


                startActivityForResult(intent, 0);



            }
        });

        ((ImageButton)findViewById(R.id.ibinseto)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                intent.putExtra("TITULO","ENTOMOFAUNA");
                intent.putExtra("GRUPO","ENTO");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));
                startActivityForResult(intent, 0);

            }
        });

        ((ImageButton)findViewById(R.id.ibanfibio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                intent.putExtra("TITULO","HERPETOFAUNA");
                intent.putExtra("GRUPO","HERP");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));
                startActivityForResult(intent, 0);

            }
        });
        ((ImageButton)findViewById(R.id.ibmorcego)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                intent.putExtra("TITULO","QUIRÓPTEROS");
                intent.putExtra("GRUPO","QUIR");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));
                startActivityForResult(intent, 0);

            }
        });
        ((ImageButton)findViewById(R.id.ibrato)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                intent.putExtra("TITULO","P. MAMÍFEROS");
                intent.putExtra("GRUPO","PMAM");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));
                startActivityForResult(intent, 0);

            }
        });
        ((ImageButton)findViewById(R.id.ibjaguatirica)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Formulario.class);
                intent.putExtra("TITULO","M. & G. MAMÍFEROS");
                intent.putExtra("GRUPO","MGMA");
                intent.putExtra("TIPO", getIntent().getStringExtra("TIPO"));
                startActivityForResult(intent, 0);

            }
        });

    }



}
