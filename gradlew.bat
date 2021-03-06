package com.fitness.android.myapplication.Practice.retro;


import com.fitness.android.myapplication.Practice.pojo.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client 
{
    
    private static final String BASE_URL = "";
    private static Client INSTANCE;
    private static GetInterface getInterface;
    public Client() 
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        getInterface = retrofit.create(GetInterface.class);
    }
    
    public static Client getINSTANCE()
    {
        if (INSTANCE != null){
            INSTANCE = new Client();
        }
        
        return INSTANCE;
    }
    
    public Call<List<DataModel>> getModels(String q)
    {
        return getInterface.getDataModels(q);
    }
    
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          