package me.akulakovsky.shopper.utils;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class Api {

    public static final String TAG = "ShopperApi";

    private static ApiService service;

    public static ApiService getInstance() {
        if (service == null) {
            init();
        }
        return service;
    }

    private static void init() {
        OkHttpClient httpClient = buildHttpClient();
        ObjectMapper jacksonMapper = buildJacksonMappger();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                .client(httpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    private static ObjectMapper buildJacksonMappger() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        return mapper;
    }

    private static OkHttpClient buildHttpClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Log.d(TAG, message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(logging);
        return clientBuilder.build();
    }
}