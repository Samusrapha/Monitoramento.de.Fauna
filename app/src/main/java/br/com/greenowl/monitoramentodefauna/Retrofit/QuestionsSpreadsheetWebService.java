package br.com.greenowl.monitoramentodefauna.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuestionsSpreadsheetWebService {

    @POST("1DeXLhLWJAxVa1ROll5a-PCtV7aVvK1aGSNNbLjYZKCo/formResponse")
    @FormUrlEncoded
    Call<Void> completeQuestionnaire(
            @Field("entry.774152857") String RESPONSAVEL,
            @Field("entry.1348408217") String OBSERVACAO,
            @Field("entry.1393186725") String CONDICOESCLIMATICAS,
            @Field("entry.1122031717") String TRANSECTO,
            @Field("entry.1566169163") String PLATO,
            @Field("entry.4621085") String AMBIENTE,
            @Field("entry.309921001") String PERIODO,
            @Field("entry.1645806292") String DATA,
            @Field("entry.1785528554") String METODO,
            @Field("entry.1560535999") String ESPECIE,
            @Field("entry.212366893") String TIPO
    );

}
