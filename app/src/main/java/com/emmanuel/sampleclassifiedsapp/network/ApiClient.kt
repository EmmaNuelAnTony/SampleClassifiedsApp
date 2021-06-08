package com.emmanuel.sampleclassifiedsapp.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {

    private var apiService: ApiService? = null
    var baseUrl = ApiUrls.BASE_URL
    val apiWithoutToken: ApiService?
        get() {
            if (apiService == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                val httpClient = OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                httpClient.networkInterceptors().add(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()

                        val request = original.newBuilder()
                            .method(original.method, original.body)
                            .build()

                        return chain.proceed(request)
                    }
                })

                httpClient.addInterceptor(logging)

                httpClient.readTimeout(2.toLong() * 60, TimeUnit.SECONDS)
                httpClient.writeTimeout(2.toLong() * 60, TimeUnit.SECONDS)

                val client = httpClient.build()

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()


                apiService = retrofit.create<ApiService>(ApiService::class.java)
            }

            return apiService
        }
    val api: ApiService?
        get() {
            if (apiService == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                val httpClient = OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS)
                httpClient.networkInterceptors().add(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val original = chain.request()

                        val request = original.newBuilder()
                            .method(original.method, original.body)
                            .build()

                        return chain.proceed(request)
                    }
                })

                httpClient.addInterceptor(logging)

                httpClient.readTimeout(2.toLong() * 60, TimeUnit.SECONDS)
                httpClient.writeTimeout(2.toLong() * 60, TimeUnit.SECONDS)

                val client = httpClient.build()

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()


                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService
        }
}
