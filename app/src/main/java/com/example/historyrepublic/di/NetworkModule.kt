package com.example.historyrepublic.di

import com.example.historyrepublic.data.local.LocalDataSource
import com.example.historyrepublic.data.local.LocalDataSourceImp
import com.example.historyrepublic.data.network.NetworkDataSource
import com.example.historyrepublic.data.network.NetworkDataSourceImp
import com.example.historyrepublic.data.network.api.HeroApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


        // let decoder: JSONDecoder = {
        //    let decoder = JSONDecoder()
        //    return decoder
        //}()
        @Provides
        fun providesMoshi(): Moshi {
            return Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        }

        // let session: URLSession = {
        //    let config = URLSessionConfiguration.default
        //    return URLSession(configuration: config)
        //}()
        @Provides
        fun providesOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
// var request = URLRequest(url: url)
//request.addValue("Bearer TOKEN", forHTTPHeaderField: "Authorization")
                .addInterceptor { chain ->
                    val original = chain.request()
                    val newRequest = original.newBuilder()
                        .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHBpcmF0aW9uIjoxNzYxMTcxMzY1LjM3NDg5OCwidXNlcm5hbWUiOiJrZXZpbkBleGFtcGxlLmNvbSIsInVzZXJJRCI6IjQ5Q0I2RDcyLUQ2RTMtNDI0Ri1CQTQxLTc1MjkwQUVDRTNDNSIsImlzUmVmcmVzaCI6ZmFsc2V9.oCJFqlBPI3Vs6iJJwUHevaj85QqgiYfhgG5wLIwG9Pl8-wZxsE8Z59ClxXDCrTbHI2CxnSIPYxBPAjCaUe1whA")
                        .build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(superheroOkHttpClient: OkHttpClient ,moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://history-republic-db.fly.dev/")
                .client(superheroOkHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }


        // protocol SuperheroesAPI {
        //    func getHeroes() async throws -> [Hero]
        //}
        @Provides
        fun provideSuperheroApi(retrofit: Retrofit): HeroApi {
            return retrofit.create(HeroApi::class.java)
        }


    @Provides
    fun provideNetworkModule(networkDataSourceImp: NetworkDataSourceImp): NetworkDataSource {
        return networkDataSourceImp
    }
}