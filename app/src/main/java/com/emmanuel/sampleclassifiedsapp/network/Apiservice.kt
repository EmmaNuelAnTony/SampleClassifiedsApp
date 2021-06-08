package com.emmanuel.sampleclassifiedsapp.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


public interface ApiService {


    @GET("")
    abstract fun hitGetApiWithoutToken(
        @Url url: String,
    ): Call<JsonObject>


}