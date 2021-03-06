package com.miramicodigo.toddler.data.rest;

import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.data.entity.PreguntaEntity;
import com.miramicodigo.toddler.data.entity.response.PreguntasResponse;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.view.Config;
import com.miramicodigo.toddler.data.entity.request.EvaluarRequest;
import com.miramicodigo.toddler.data.entity.response.EvaluarResponse;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;

/**
 * Created by gusn8 on 31-01-17.
 */

public class ApiClient {

    private static ServicesApiInterface servicesApiInterface;

    public static ServicesApiInterface getMyApiClient() {
        if (servicesApiInterface == null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Config.HOST)
                    .setClient(new OkClient(getClient()))
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
            servicesApiInterface = restAdapter.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {
        @FormUrlEncoded
        @POST("/evaluar.php")
        void obtieneEvaluacion(
                @Field("nombre") String nombre,
                @Field("ci") int ci,
                @Field("edad") int edad,
                @Field("nombreTutor") String nombreTutor,
                @Field("resGrueso") int resGrueso,
                @Field("resFino") int resFino,
                @Field("resLeng") int resLeng,
                Callback<EvaluarEntity> callback
        );

        @Headers("Content-Type: application/json; charset=UTF-8")
        @GET("/listaPreguntas.php")
        void obtienePreguntas(Callback<List<Preguntas>> callback);

    }

    private static OkHttpClient getClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(2, TimeUnit.MINUTES);
        client.setReadTimeout(2, TimeUnit.MINUTES);
        return client;
    }
}
