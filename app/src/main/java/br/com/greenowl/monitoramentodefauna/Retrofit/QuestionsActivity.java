package br.com.greenowl.monitoramentodefauna.Retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import br.com.greenowl.monitoramentodefauna.App.MessageBox;
import br.com.greenowl.monitoramentodefauna.Dominio.Entidade.Registros;
import br.com.greenowl.monitoramentodefauna.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuestionsActivity extends AppCompatActivity {

    private TextView RESPONSAVEL;
    private TextView OBSERVACAO;
    private TextView CONDICOESCLIMATICAS;
    private TextView TRANSECTO;
    private TextView PLATO;
    private TextView AMBIENTE;
    private TextView PERIODO;
    private TextView DATA;
    private TextView METODO;
    private TextView ESPECIE;
    private TextView TIPO;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/d/")
            .build();


          //  nameInputField = (TextView) findViewById(R.id.question_name_input);
          // catQuestionInputField = (CheckBox) findViewById(R.id.question_cats_input);



    }

    public void Exportaplanilha (ArrayList<Registros> contatos) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();


        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);
        try {

            for (int i =0; i<contatos.size();i++) {
                String  RESPONSAVEL =contatos.get(i).getRESPONSAVEL();
                String  OBSERVACAO=contatos.get(i).getOBSERVACAO();
                String  CONDICOESCLIMATICAS=contatos.get(i).getCONDICOESCLIMATICAS();
                String  TRANSECTO=contatos.get(i).getTRANSECTO();
                String  PLATO=contatos.get(i).getPLATO();
                String  AMBIENTE=contatos.get(i).getAMBIENTE();
                String  PERIODO=contatos.get(i).getPERIODO();
                String    DATA=contatos.get(i).getDATA().toString();
                String  METODO=contatos.get(i).getMETODO();
                String  ESPECIE=contatos.get(i).getESPECIE();
                String  TIPO=contatos.get(i).getTIPO();
                Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(RESPONSAVEL, OBSERVACAO,CONDICOESCLIMATICAS,TRANSECTO,PLATO,AMBIENTE,PERIODO,DATA,METODO,ESPECIE,TIPO);
                completeQuestionnaireCall.enqueue(callCallback);
            }
                // staff elements


        }
    catch (Exception ex){
        MessageBox.show(this, "Erro", "Erro ao criar o banco " + ex.getMessage());
    }

            };



    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }


    };




}
